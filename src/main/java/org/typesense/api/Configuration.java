package org.typesense.api;

import org.typesense.resources.Node;

import java.time.Duration;
import java.util.List;

/**
 * Typesense configuration API wrapper.
 */
public class Configuration {

    public List<Node> nodes;
    public Node nearestNode;
    public Duration connectionTimeout;
    public Duration readTimeout;
    public Duration healthCheckInterval;
    public int numRetries;
    public Duration retryInterval;
    public String apiKey;
    public boolean sendApiKeyAsQueryParam;

    /**
     *
     * @param nodes List of Nodes
     * @param connectionTimeout Duration in seconds
     * @param apiKey String describing the apiKey
     */

    /**
     * Creates a new Configuration instance.
     * @param nodes the {@code List<Node>} nodes
     * @param connectionTimeout the {@code Duration} connection timeout
     * @param apiKey the {@code String} api key
     */
    public Configuration(List<Node> nodes, Duration connectionTimeout, String apiKey) {
        this.nodes = nodes;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = connectionTimeout;
        this.apiKey = apiKey;

        this.healthCheckInterval = Duration.ofSeconds(60);
        this.numRetries =  3;
        this.retryInterval= Duration.ofSeconds(3);
        this.sendApiKeyAsQueryParam = false;
    }
    
    /**
     *
     * @param nodes List of Nodes
     * @param connectionTimeout Duration in seconds
     * @param readTimeout Duration in seconds
     * @param apiKey String describing the apiKey
     */
    public Configuration(List<Node> nodes, Duration connectionTimeout, Duration readTimeout, String apiKey) {
        this.nodes = nodes;
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
        this.apiKey = apiKey;

        this.healthCheckInterval = Duration.ofSeconds(60);
        this.numRetries =  3;
        this.retryInterval= Duration.ofSeconds(3);
        this.sendApiKeyAsQueryParam = false;
    }

    /**
     * Creates a new Configuration instance.
     * @param nearestNode the {@code Node} nearest node
     * @param nodes the {@code List<Node>} nodes
     * @param connectionTimeout the {@code Duration} connection timeout
     * @param apiKey the {@code String} api key
     */
    public Configuration(Node nearestNode, List<Node> nodes, Duration connectionTimeout, String apiKey) {
        this(nodes,connectionTimeout,apiKey);
        this.nearestNode = nearestNode;
    }
}
