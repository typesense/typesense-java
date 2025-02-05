package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.typesense.TypesenseContainer;

import java.util.HashMap;

@Testcontainers
class HealthAndOperationsTest {

    @Container
    static TypesenseContainer typesense = new TypesenseContainer(Helper.IMAGE);    
    
    private Client client;
    private Helper helper;

    @BeforeEach
    void setUp() throws Exception {
        helper = new Helper(typesense);
        client = helper.getClient();
    }

    @AfterEach
    void tearDown() throws Exception {
        helper.teardown();
    }

    @Test
    void testRetrieve() throws Exception {
        System.out.println(this.client.health.retrieve());
    }

    @Test
    void testPerformSnapshot() throws Exception {
        HashMap<String, String> query = new HashMap<>();
        query.put("snapshot_path", "/tmp/typesense-data-snapshot");
        System.out.println(client.operations.perform("snapshot", query));
    }

    @Test
    void testPerformVote() throws Exception {
        System.out.println(client.operations.perform("vote"));
    }

    @Test
    void testMetrics() throws Exception {
        System.out.println(client.metrics.retrieve());
    }

    @Test
    void testDebug() throws Exception {
        System.out.println(client.debug.retrieve());
    }
}