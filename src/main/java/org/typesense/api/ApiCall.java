package org.typesense.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okio.BufferedSink;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.typesense.api.exceptions.*;
import org.typesense.model.ErrorResponse;
import org.typesense.resources.Node;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class ApiCall {

    private final Configuration configuration;
    private final List<Node> nodes;

    private static int nodeIndex = 0;

    private static final String API_KEY_HEADER = "X-TYPESENSE-API-KEY";
    private static final Logger logger = LogManager.getLogger(ApiCall.class);
    private final OkHttpClient client;
    private final String apiKey;
    private final Duration retryInterval;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final ObjectMapper mapper = new ObjectMapper();

    public ApiCall(Configuration configuration) {
        this.configuration = configuration;
        this.nodes = configuration.nodes;
        this.apiKey = configuration.apiKey;
        this.retryInterval = configuration.retryInterval;

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(configuration.connectionTimeout.getSeconds(), TimeUnit.SECONDS)
                .readTimeout(configuration.connectionTimeout.getSeconds(), TimeUnit.SECONDS)
                .build();
    }

    boolean isDueForHealthCheck(Node node) {
        return Duration.between(node.lastAccessTimestamp, LocalDateTime.now()).getSeconds() > configuration.healthCheckInterval.getSeconds();
    }

    //Loops in a round-robin fashion to check for a healthy node and returns it
    Node getNode() {
        if (configuration.nearestNode != null) {
            if (isDueForHealthCheck((configuration.nearestNode)) || configuration.nearestNode.isHealthy) {
                return configuration.nearestNode;
            }
        }

        int i = 0;
        Node testNode;

        while (i < configuration.nodes.size()) {
            testNode = configuration.nodes.get(nodeIndex);

            if (testNode.isHealthy || isDueForHealthCheck((testNode))) {
                return testNode;
            }

            i += 1;
            nodeIndex = (nodeIndex + 1) % configuration.nodes.size();
        }

        return configuration.nodes.get(nodeIndex);
    }

    void setNodeHealthStatus(Node node, boolean status) {
        node.isHealthy = status;
        node.lastAccessTimestamp = LocalDateTime.now();
    }

    private TypesenseError getException(Response response) throws IOException {
        ErrorResponse errorResponse = mapper.readValue(response.body().string(), ErrorResponse.class);
        String message = errorResponse.getMessage();
        int status_code = response.code();

        if (status_code == 400)
            return new RequestMalformed(message, status_code);
        else if (status_code == 401)
            return new RequestUnauthorized(message, status_code);
        else if (status_code == 403)
            return new RequestForbidden(message, status_code);
        else if (status_code == 404)
            return new ObjectNotFound(message, status_code);
        else if (status_code == 409)
            return new ObjectAlreadyExists(message, status_code);
        else if (status_code == 422)
            return new ObjectUnprocessable(message, status_code);
        else if (status_code == 500)
            return new ServerError(message, status_code);
        else if (status_code == 503)
            return new ServiceUnavailable(message, status_code);
        else
            return new HttpError(message, status_code);
    }

    <Q, R> R get(String endpoint, Q queryParameters, Class<R> responseClass) throws Exception {
        Request.Builder rb = new Request.Builder().get();
        return makeRequest(endpoint, queryParameters, rb, responseClass);
    }

    <B, Q, R> R put(String endpoint, B body, Q queryParameters, Class<R> responseClass) throws Exception {
        RequestBody requestBody = RequestBody.create(mapper.writeValueAsString(body), JSON);
        Request.Builder rb = new Request.Builder().put(requestBody);
        return makeRequest(endpoint, queryParameters, rb, responseClass);
    }

    <B, Q, R> R patch(String endpoint, B body, Q queryParameters, Class<R> responseClass) throws Exception {
        RequestBody requestBody = RequestBody.create(mapper.writeValueAsString(body), JSON);
        Request.Builder rb = new Request.Builder().patch(requestBody);
        return makeRequest(endpoint, queryParameters, rb, responseClass);
    }

    <B, Q, R> R post(String endpoint, B body, Q queryParameters, Class<R> responseClass) throws Exception {
        RequestBody requestBody = RequestBody.create(mapper.writeValueAsString(body), JSON);
        Request.Builder rb = new Request.Builder().post(requestBody);
        return makeRequest(endpoint, queryParameters, rb, responseClass);
    }

    <Q, R> R delete(String endpoint, Q queryParameters, Class<R> responseClass) throws Exception {
        Request.Builder rb = new Request.Builder().delete();
        return makeRequest(endpoint, queryParameters, rb, responseClass);
    }

    <B, Q, R> R postBulkImport(String endpoint, B body, Q queryParameters, Class<R> responseClass) throws Exception {
        RequestBody requestBody = getRequestBodyForBulkImport(body);
        Request.Builder rb = new Request.Builder().post(requestBody);
        return makeRequest(endpoint, queryParameters, rb, responseClass);
    }

    private <B> RequestBody getRequestBodyForBulkImport(B body) {
        return new RequestBody() {
            @java.lang.Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                // Write each JSON object as a separate line in the request body
                String[] lines = body.toString().split("\n");
                for (String line : lines) {
                    bufferedSink.writeUtf8(line + "\n");
                }
            }

            @java.lang.Override
            public MediaType contentType() {
                return MediaType.parse("application/json");
            }
        };
    }

    <Q, T> T makeRequest(String endpoint, Q queryParameters, Request.Builder requestBuilder,
                         Class<T> responseClass) throws Exception {
        int num_tries = 0;
        Logger logger = LogManager.getLogger(ApiCall.class);
        Exception lastException = new TypesenseError("Unknown client error", 400);

        while (num_tries < this.configuration.numRetries) {
            num_tries += 1;
            Node node = this.getNode();
            String URI = node.baseUrl;

            try {
                String url = URI + endpoint;
                String fullUrl = populateQueryParameters(url, queryParameters).toString();
                Request request = requestBuilder
                                  .url(fullUrl)
                                  .header(API_KEY_HEADER, apiKey)
                                  .build();

                Response response = client.newCall(request).execute();

                if (response.code() < 500) {
                    // any non-50x status code means that the node is healthy
                    this.setNodeHealthStatus(node, true);
                }

                if (response.code() >= 200 && response.code() < 300) {
                    return handleResponse(response, responseClass);
                }

                throw getException(response);

            } catch (Exception e) {
                boolean handleError = (e instanceof ServerError) ||
                                      (e instanceof ServiceUnavailable) ||
                                      (e instanceof SocketTimeoutException) ||
                                      (e instanceof java.net.UnknownHostException) ||
                                      (e instanceof SSLException);

                if(!handleError) {
                    // we just throw and move on
                    throw e;
                }

                this.setNodeHealthStatus(node, false);
                lastException = e;
                logger.trace("Request to " + node.host + " failed because: " + e.getMessage());
                logger.trace("Sleeping for " + this.retryInterval.getSeconds() + "s and then retrying request");
                try {
                    Thread.sleep(this.retryInterval.getSeconds() * 1000);
                } catch (InterruptedException interruptedException) {
                    logger.error("Error while sleeping.", interruptedException);
                }
            }
        }

        throw lastException;
    }

    private <T> HttpUrl.Builder populateQueryParameters(String url, T queryParameters) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();

        if (queryParameters != null) {
            Map<String, Object> map = mapper.convertValue(queryParameters, Map.class);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                StringBuilder value = new StringBuilder();
                if (entry.getValue() instanceof ArrayList) {
                    for (int i = 0; i < ((ArrayList<?>) entry.getValue()).size(); i++) {
                        value.append(((ArrayList<?>) entry.getValue()).get(i));
                        if (i != ((ArrayList<?>) entry.getValue()).size() - 1)
                            value.append(",");
                    }
                    httpBuilder.addQueryParameter(entry.getKey(), value.toString());
                } else {
                    httpBuilder.addQueryParameter(entry.getKey(), entry.getValue().toString());
                }
            }
        }

        return httpBuilder;
    }

    /**
     * Function to create a map from the json response
     *
     * @param response Jersey Response object
     * @param <T>
     * @return HashMap containing the response
     */

    <T> T handleResponse(Response response, Class<T> responseClass) throws IOException {
        if (responseClass == null) {
            try {
                return (T) mapper.readValue(response.body().string(), HashMap.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (responseClass == String.class) {
                    return (T) response.body().string();
                }
                return mapper.readValue(response.body().string(), responseClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}

