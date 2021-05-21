package org.typesense.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

class Node{

    public String protocol;
    public String host;
    public String path;
    public String port;
    public String baseUrl;

    public boolean isHealthy;
    public LocalDateTime lastAccessTimestamp;

    public Node(String protocol, String host, String port) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.isHealthy = true;

        if(protocol==null) {
            throw new RuntimeException("Protocol cannot be null");
        }

        if(host==null){
            throw new RuntimeException("Host cannot be null");
        }

        if(port==null){
            throw new RuntimeException("Port cannot be null");
        }

        this.baseUrl = protocol + "://" + host + ":" +port;
    }

}

public class Configuration {

    public List<Node> nodes;
    public Node nearestNode;
    public Duration connectionTimeout;
    public Duration healthCheckInterval;
    public int numRetries;
    public Duration retryInterval;
    public String apiKey;
    public boolean sendApiKeyAsQueryParam;

    public Configuration(List<Node> nodes, Duration connectionTimeout, String apiKey) {
        this.nodes = nodes;
        this.connectionTimeout = connectionTimeout;
        this.apiKey = apiKey;

        this.healthCheckInterval = Duration.ofSeconds(15);
        this.numRetries =  3;
        this.retryInterval= Duration.ofSeconds(100);
        this.sendApiKeyAsQueryParam = false;
    }

    public Configuration(Node nearestNode, List<Node> nodes, Duration connectionTimeout, String apiKey) {
        this(nodes,connectionTimeout,apiKey);
        this.nearestNode = nearestNode;
    }
}
