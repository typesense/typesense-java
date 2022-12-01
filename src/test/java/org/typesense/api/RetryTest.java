package org.typesense.api;

import junit.framework.TestCase;
import org.typesense.resources.Node;

import java.net.UnknownHostException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class RetryTest extends TestCase {

    private Client client;

    public void setUp() throws Exception {
        super.setUp();
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http","unknownhost123","8108"));
        Configuration configuration = new Configuration(nodes, Duration.ofSeconds(1),"xyz");
        configuration.numRetries = 2;
        configuration.retryInterval = Duration.ofSeconds(2);
        this.client = new Client(configuration);
    }

    public void testRetry() throws Exception {
        long start = System.currentTimeMillis();
        try {
            client.health.retrieve();
        } catch (Exception e) {
            long timeTaken = System.currentTimeMillis() - start;
            assertTrue(e instanceof java.net.UnknownHostException);
            assertTrue(timeTaken > 4000);
            return ;
        }

        throw new Exception("Retry did not throw exception.");
    }
}