package org.typesense.api;

import java.util.Map;

public class Metrics {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/metrics.json";

    public Metrics(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public Map<String, String> retrieve() throws Exception {
        return this.apiCall.get(RESOURCEPATH, null, Map.class);
    }
}
