package org.typesense.api;

import java.util.Map;

/**
 * Typesense health API wrapper.
 */
public class Health {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/health";

    /**
     * Creates a new Health instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public Health(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    /**
     * Checks if Typesense server is ready to accept requests.
     *
     * <p>
     * HTTP: GET /health
     *
     * @return the {@code Map<String,Object>} response map
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/cluster-operations.html#health">Typesense docs</a>
     */
    public Map<String, Object> retrieve() throws Exception {
        return this.apiCall.get(RESOURCEPATH, null, Map.class);
    }
}
