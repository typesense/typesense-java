package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.typesense.resources.Node;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class APICallTest {

    private ApiCall apiCall;

    @BeforeEach
    void setUp() throws Exception {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("http","localhost","8108"));
        nodes.add(new Node("http","localhost","7108"));
        nodes.add(new Node("http","localhost","6108"));
        apiCall = new ApiCall(new Configuration(nodes, Duration.ofSeconds(3),"xyz"));
    }

    @AfterEach
    void tearDown() throws Exception {

    }

    @Test
    void testRoundRobin() throws Exception {
        assertEquals("7108", apiCall.getNode().port);
        assertEquals("6108", apiCall.getNode().port);
        assertEquals("8108", apiCall.getNode().port);

        assertEquals("7108", apiCall.getNode().port);
        assertEquals("6108", apiCall.getNode().port);
        assertEquals("8108", apiCall.getNode().port);
    }
}