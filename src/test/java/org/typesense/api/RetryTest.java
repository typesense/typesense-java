package org.typesense.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RetryTest {

    private Client client;

    @BeforeEach
    void setUp() throws Exception {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http", "unknownhost123", "8108"));
        Configuration configuration = new Configuration(nodes, Duration.ofSeconds(1), () -> "xyz");
        configuration.numRetries = 2;
        configuration.retryInterval = Duration.ofSeconds(2);
        this.client = new Client(configuration);
    }

    @Test
    void testRetry() throws Exception {
        long start = System.currentTimeMillis();
        try {
            client.health.retrieve();
        } catch (Exception e) {
            long timeTaken = System.currentTimeMillis() - start;
            assertTrue(e instanceof java.net.UnknownHostException);
            assertTrue(timeTaken > 4000);
            return;
        }

        throw new Exception("Retry did not throw exception.");
    }
}