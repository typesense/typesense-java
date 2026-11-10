package org.typesense.api;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.Test;
import org.typesense.resources.Node;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClientTest {

    @Test
    void testCustomOkHttpClientServesSynonymAndCurationSets() throws Exception {
        OkHttpClient okHttpClient = mock(OkHttpClient.class);
        Call call = mock(Call.class);
        when(okHttpClient.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenThrow(new IOException("no server"));

        Configuration configuration = new Configuration(
                Arrays.asList(new Node("http", "localhost", "8108")), Duration.ofSeconds(3), "xyz");
        Client client = new Client(configuration, okHttpClient);

        assertThrows(IOException.class, () -> client.synonymSets().retrieve());
        assertThrows(IOException.class, () -> client.synonymSet("products").retrieve());
        assertThrows(IOException.class, () -> client.curationSets().retrieve());
        assertThrows(IOException.class, () -> client.curationSet("products").retrieve());
        verify(okHttpClient, times(4)).newCall(any(Request.class));
    }

    @Test
    void testDefaultConstructorResetsNodeIndex() throws Exception {
        Configuration configuration = new Configuration(
                Arrays.asList(new Node("http", "localhost", "8108"), new Node("http", "localhost", "7108")),
                Duration.ofSeconds(3), "xyz");
        new ApiCall(configuration, mock(OkHttpClient.class)).getNode();

        new Client(configuration);

        Field nodeIndex = ApiCall.class.getDeclaredField("nodeIndex");
        nodeIndex.setAccessible(true);
        assertEquals(0, nodeIndex.get(null));
    }
}
