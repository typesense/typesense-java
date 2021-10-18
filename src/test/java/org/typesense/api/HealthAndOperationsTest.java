package org.typesense.api;

import junit.framework.TestCase;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class HealthAndOperationsTest extends TestCase {

    private Client client;
    private Helper helper;

    public void setUp() throws Exception {
        super.setUp();
        helper = new Helper();
        client = helper.getClient();
    }

    public void tearDown() throws Exception {
        super.tearDown();
        helper.teardown();
    }

    public void testRetrieve() {
        System.out.println(this.client.health.retrieve());
    }

    public void testPerformSnapshot(){
        HashMap<String, String> query = new HashMap<>();
        query.put("snapshot_path","/tmp/typesense-data-snapshot");
        System.out.println(client.operations.perform("snapshot",query));
    }

    public void testPerformVote(){
        System.out.println(client.operations.perform("vote"));
    }

    public void testMetrics(){
        System.out.println(client.metrics.retrieve());
    }

    public void testDebug(){
        System.out.println(client.debug.retrieve());
    }
}