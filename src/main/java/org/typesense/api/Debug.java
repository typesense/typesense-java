package org.typesense.api;

import java.util.Map;

/**
 * Typesense debug API wrapper.
 */
public class Debug {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/debug";

    /**
     * Creates a new Debug instance.
     * @param apiCall the {@code ApiCall} instance used to send requests
     */
    public Debug(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    /**
     * Print debugging information.
     *
     * <p>
     * HTTP: GET /debug
     *
     * @return the {@code Map<String,Object>} response map
     * @throws Exception if the request fails
     *
     * @see <a href="https://typesense.org/docs/latest/api/cluster-operations.html#debug">Typesense docs</a>
     */
    public Map<String, Object> retrieve() throws Exception {
        return this.apiCall.get(RESOURCEPATH, null, Map.class);
    }
}
