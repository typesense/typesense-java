package org.typesense.model;

import java.time.Duration;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.jackson.JacksonFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.typesense.TypesenseClient;
import org.typesense.api.SearchParameters;
import org.typesense.interceptor.LoggingInterceptor;
import org.typesense.resources.Node;
import org.typesense.resources.RequestHandler;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;


public class Api {

    private final Configuration configuration;
    private final List<Node> nodes;

    private static int nodeIndex=0;

    private static final String API_KEY_HEADER = "X-TYPESENSE-API-KEY";
    private static final Logger logger = LoggerFactory.getLogger(TypesenseClient.class);
    private final Client client;
    private final String apiKey;

    public Api(Configuration configuration) {
        this.configuration = configuration;
        this.nodes = configuration.nodes;
        this.apiKey = configuration.apiKey;

        ClientConfig clientConfig = new ClientConfig();
        if (logger.isTraceEnabled()) {
            clientConfig = clientConfig.register(new LoggingInterceptor());
        }
        // TODO: SET_METHOD_WORKAROUND Generates an illegal reflective access operation for the patch op
        this.client = ClientBuilder.newClient(clientConfig)
                .register(JacksonFeature.class)
                .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
    }

    boolean isDueForHealthCheck(Node node){
        return Duration.between(node.lastAccessTimestamp, LocalDateTime.now()).getSeconds() > configuration.healthCheckInterval.getSeconds();
    }

    //Loops in a round-robin fashion to check for a healthy node and returns it
    Node getNode(){
        if(configuration.nearestNode!=null){
            if(isDueForHealthCheck((configuration.nearestNode) ) || configuration.nearestNode.isHealthy){
                return configuration.nearestNode;
            }
        }

        int i=0;
        Node testNode;

        while(i < configuration.nodes.size()){
            testNode = configuration.nodes.get(nodeIndex);

            if(testNode.isHealthy || isDueForHealthCheck((testNode))){
                return testNode;
            }

            i += 1;
            nodeIndex = (nodeIndex+1)%configuration.nodes.size();
        }

        return configuration.nodes.get(nodeIndex);
    }

    void setNodeHealthStatus(Node node, boolean status){
        node.isHealthy = status;
        node.lastAccessTimestamp = LocalDateTime.now();
    }

    <T> T get(String endpoint, Class<T> resourceClass, SearchParameters searchParameters){

        /**
         * Lambda function which implements the RequestHandler interface
         * which is passed as a parameter to makeRequest function
         * and returns T as the response entity.
         * This is similar for all type of requests.
         */
        RequestHandler<T> r =  (String REST_URI) -> populateSearchParameters(this.client.target(REST_URI),searchParameters)
                .request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER,apiKey)
                .get(resourceClass);

        return makeRequest(endpoint,r);
    }

    <T> T get(String endpoint, Class<T> resourceClass){
        RequestHandler<T> r =  (String REST_URI) -> this.client.target(REST_URI)
                 .request(MediaType.APPLICATION_JSON)
                 .header(API_KEY_HEADER,apiKey)
                 .get(resourceClass);

         return makeRequest(endpoint,r);
    }

    <T> T get(String endpoint){
        RequestHandler r =  (String REST_URI) -> this.client.target(REST_URI)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(API_KEY_HEADER,apiKey)
                .get();

        Response response = makeRequest(endpoint,r);

        return handleResponse(response);
    }

    <T> T put(String endpoint, T body, Class<T> resourceClass){

        RequestHandler<T> r =  (String REST_URI) -> this.client.target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER,apiKey)
                .put(Entity.json(body),resourceClass);

        return makeRequest(endpoint,r);
    }

    <T, R> T post(String endpoint, R body, Class<T> resourceClass){

        RequestHandler<T> r =  (String REST_URI) -> this.client.target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER,apiKey)
                .post(Entity.json(body),resourceClass);

        return makeRequest(endpoint,r);
    }

    <T> T post(String endpoint, T body){

        RequestHandler r =  (String REST_URI) -> this.client.target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER,apiKey)
                .post(Entity.json(body));

        Response response = makeRequest(endpoint,r);

        return handleResponse(response);
    }

    <T> T delete(String endpoint,  HashMap<String, String> queryParameters){
        RequestHandler r =  (String REST_URI) -> populateQueryParameters(this.client.target(REST_URI),queryParameters)
                .request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER,apiKey)
                .delete();


        Response response = makeRequest(endpoint,r);

        return handleResponse(response);
    }


    <T> T delete(String endpoint,  Class<T> resourceClass){
        RequestHandler<T> r =  (String REST_URI) -> this.client.target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER,apiKey)
                .delete(resourceClass);

        return makeRequest(endpoint,r);
    }

    <T> T delete(String endpoint){
        RequestHandler r =  (String REST_URI) -> this.client.target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER,apiKey)
                .delete();

        Response response = makeRequest(endpoint,r);

        return handleResponse(response);
    }

    /**
     * Actual function which makes the http request
     * @param endpoint particular endpoint of the resource
     * @param requestHandler lambda function interface for the particular request
     * @param <T> response entity
     * @return http response
     */

    <T> T makeRequest(String endpoint, RequestHandler requestHandler){
        int num_tries = 0;

        T responseBody;

        while(num_tries < this.configuration.numRetries){
            num_tries += 1;

            Node node = this.getNode();

            String URI = node.baseUrl;
                    //node.baseUrl;
            try{
                String url = URI + endpoint;
                responseBody = (T) requestHandler.handleRequest(url);

                return responseBody;
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        return  null;
    }

    /**
     * Appends search query parameters to the request URL.
     *
     * @param client a WebTarget target instance.
     * @param parameters a SearchParameters instance.
     * @return a WebTarget with the relevant search parameters added.
     */
    private WebTarget populateSearchParameters(WebTarget client, SearchParameters parameters) {
        for (Map.Entry<String, String> entry : parameters.getParameters().entrySet()) {
            client = client.queryParam(entry.getKey(), entry.getValue());
        }
        return client;
    }

    /**
     * Adds query parameters to the http request
     * @param client WebTarget object pointing to the required endpoint
     * @param queryParameters Map of the query parameters
     * @return WebTarget with the query parameters added
     */

    private WebTarget populateQueryParameters(WebTarget client, HashMap<String, String> queryParameters) {
        for (Map.Entry<String, String> entry : queryParameters.entrySet()) {
            client = client.queryParam(entry.getKey(), entry.getValue());
        }
        return client;
    }

    /**
     * Function to create a map from the json response
     * @param response Jersey Response object
     * @param <T>
     * @return HashMap containing the response
     */

    private<T> T handleResponse(Response response){

        ObjectMapper mapper = new ObjectMapper();
        String json = response.readEntity(String.class);

        try {
            HashMap<String, Object> map = mapper.readValue(json, HashMap.class);
            return (T)map;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}


