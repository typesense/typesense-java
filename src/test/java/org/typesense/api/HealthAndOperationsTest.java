package org.typesense.api;

import junit.framework.TestCase;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class HealthAndOperationsTest extends TestCase {

    private Client client;

    public void setUp() throws Exception {
        super.setUp();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http","localhost","3001"));

        Configuration configuration = new Configuration(nodes, Duration.ofSeconds(3),"xyz");

        this.client = new Client(configuration);
    }

    public void testRetrieve() {
        System.out.println(this.client.health.retrieve());
    }

    public void testPerformSnapshot(){
        HashMap<String, String> query = new HashMap<>();
        query.put("snapshot_path","/tmp/typesense-data-snapshot");
        System.out.println(this.client.operations.perform("snapshot",query));
    }

    public void testPerformVote(){
        System.out.println(this.client.operations.perform("vote"));
    }

    public void testMetrics(){
        System.out.println(this.client.metrics.retrieve());
    }

    public void testDebug(){
        System.out.println(this.client.debug.retrieve());
    }
}