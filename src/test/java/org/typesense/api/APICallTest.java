package org.typesense.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.typesense.resources.Node;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.lang.reflect.Field;
import java.net.ConnectException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class APICallTest {

    @Mock
    private OkHttpClient client;

    @Mock
    private Call call;

    private ApiCall apiCall;
    private Node nearestNode;
    private List<Node> nodes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        nodes = new ArrayList<>();
        nodes.add(new Node("http", "localhost", "8108"));
        nodes.add(new Node("http", "localhost", "7108"));
        nodes.add(new Node("http", "localhost", "6108"));
    }

    private void resetNodeIndex() throws Exception {
        Field nodeIndexField = ApiCall.class.getDeclaredField("nodeIndex");
        nodeIndexField.setAccessible(true);
        nodeIndexField.set(null, 0);
    }

    void setUpNoNearestNode() {
        apiCall = new ApiCall(new Configuration(nodes, Duration.ofSeconds(3), "xyz"), client);
    }

    void setUpNearestNode() {
        nearestNode = new Node("http", "localhost", "0000");
        apiCall = new ApiCall(new Configuration(nearestNode, nodes, Duration.ofSeconds(3), "xyz"), client);
    }

    @AfterEach
    void tearDown() throws Exception {
        nodes = null;
        apiCall = null;
        resetNodeIndex();
    }

    @Test
    void testRoundRobin() {
        setUpNoNearestNode();
        assertEquals("7108", apiCall.getNode().port);
        assertEquals("6108", apiCall.getNode().port);
        assertEquals("8108", apiCall.getNode().port);

        assertEquals("7108", apiCall.getNode().port);
        assertEquals("6108", apiCall.getNode().port);
        assertEquals("8108", apiCall.getNode().port);
    }

    @Test
    void testMakeRequestWithConnectException() throws Exception {
        setUpNoNearestNode();
        String endpoint = "/collections";
        Request.Builder requestBuilder = new Request.Builder().get();

        Call mockCall = mock(Call.class);
        when(client.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenThrow(new ConnectException());

        // Act
        assertThrows(ConnectException.class, () -> {
            apiCall.makeRequest(endpoint, null, requestBuilder, String.class);
        });

        // Additional assertions
        nodes.forEach(node -> {
            assertEquals(false, node.isHealthy);
        });

        verify(client, times(3)).newCall(any(Request.class));
        verify(mockCall, times(3)).execute();
    }

    @Test
    void testMakeRequestWithSocketTimeoutException() throws Exception {
        setUpNoNearestNode();
        String endpoint = "/collections";
        Request.Builder requestBuilder = new Request.Builder().get();

        Call mockCall = mock(Call.class);
        when(client.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenThrow(new java.net.SocketTimeoutException());

        // Act
        assertThrows(java.net.SocketTimeoutException.class, () -> {
            apiCall.makeRequest(endpoint, null, requestBuilder, String.class);
        });

        // Additional assertions
        nodes.forEach(node -> {
            assertEquals(false, node.isHealthy);
        });

        verify(client, times(3)).newCall(any(Request.class));
        verify(mockCall, times(3)).execute();
    }

    @Test
    void testMakeRequestWithUnknownHostException() throws Exception {
        setUpNoNearestNode();
        String endpoint = "/collections";
        Request.Builder requestBuilder = new Request.Builder().get();

        Call mockCall = mock(Call.class);
        when(client.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenThrow(new java.net.UnknownHostException());

        // Act
        assertThrows(java.net.UnknownHostException.class, () -> {
            apiCall.makeRequest(endpoint, null, requestBuilder, String.class);
        });

        // Additional assertions
        nodes.forEach(node -> {
            assertEquals(false, node.isHealthy);
        });

        verify(client, times(3)).newCall(any(Request.class));
        verify(mockCall, times(3)).execute();
    }

    @Test
    void testUnhealthyNearestNode() {
        setUpNearestNode();
        nearestNode.isHealthy = false;
        assertEquals("7108", apiCall.getNode().port);
    }

    @Test
    void testHealthyNearestNode() {
        setUpNearestNode();
        assertEquals("0000", apiCall.getNode().port);
    }

    @Test
    void testUnhealthyNearestNodeDueForHealthCheck() {
        setUpNearestNode();
        nearestNode.isHealthy = false;
        nearestNode.lastAccessTimestamp = nearestNode.lastAccessTimestamp.minusSeconds(63);
        assertEquals("0000", apiCall.getNode().port);
    }
}