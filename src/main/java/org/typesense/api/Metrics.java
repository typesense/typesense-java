package org.typesense.api;

import java.util.Map;

/**
 * Typesense metrics API wrapper.
 */
public class Metrics {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/metrics.json";

    /**
     * Creates a new Metrics instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public Metrics(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    /**
     * Get current RAM, CPU, Disk and Network usage metrics.
     *
     * <p>
     * Retrieve the metrics.
     *
     * <p>
     * HTTP: GET /metrics.json
     *
     * @return the {@code Map<String,String>} response map
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/cluster-operations.html">Typesense docs</a>
     */
    public Map<String, String> retrieve() throws Exception {
        return this.apiCall.get(RESOURCEPATH, null, Map.class);
    }
}
