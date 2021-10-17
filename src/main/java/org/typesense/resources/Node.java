package org.typesense.resources;

import java.time.LocalDateTime;

public class Node {

    public String protocol;
    public String host;
    public String path;
    public String port;
    public String baseUrl;

    public boolean isHealthy;
    public LocalDateTime lastAccessTimestamp;

    /**
     *
     * @param protocol String describing the protocol
     * @param host String describing the host
     * @param port String describing the port
     */

    public Node(String protocol, String host, String port) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.isHealthy = true;

        if (protocol == null) {
            throw new RuntimeException("Protocol cannot be null");
        }

        if (host == null) {
            throw new RuntimeException("Host cannot be null");
        }

        if (port == null) {
            throw new RuntimeException("Port cannot be null");
        }

        this.baseUrl = protocol + "://" + host + ":" + port;
    }

}
