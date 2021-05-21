package org.typesense.model;

import java.time.Duration;
import java.time.LocalDateTime;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.typesense.TypesenseClient;
import org.typesense.interceptor.LoggingInterceptor;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;


/**
Interface for the lambda functions
*/
 interface RequestHandler<T>{
     T handleRequest(String URI);
}

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

    <T> T get(String endpoint, Class<T> resourceClass){

        /**
         * Lambda function which implements the RequestHandler interface
         * which is passed as a parameter to makeRequest function
         * and returns T as the response entity.
         * This is similar for all type of requests.
         */
        RequestHandler<T> r =  (String REST_URI) -> this.client.target(REST_URI)
                 .request(MediaType.APPLICATION_JSON)
                 .header(API_KEY_HEADER,apiKey)
                 .get(resourceClass);

         return makeRequest(endpoint,r);
    }

    <T> T put(String endpoint, T body, Class<T> resourceClass){

        RequestHandler<T> r =  (String REST_URI) -> this.client.target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER,apiKey)
                .put(Entity.json(body),resourceClass);

        return makeRequest(endpoint,r);
    }

    <T> T post(String endpoint, T body, Class<T> resourceClass){

        RequestHandler<T> r =  (String REST_URI) -> this.client.target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER,apiKey)
                .post(Entity.json(body),resourceClass);

        return makeRequest(endpoint,r);
    }

    <T> T delete(String endpoint,  Class<T> resourceClass){
        RequestHandler<T> r =  (String REST_URI) -> this.client.target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .header(API_KEY_HEADER,apiKey)
                .delete(resourceClass);

        return makeRequest(endpoint,r);
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

            String URI = "http://localhost:3001";
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
}
