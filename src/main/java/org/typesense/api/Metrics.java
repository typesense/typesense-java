package org.typesense.api;

import org.typesense.api.exceptions.TypesenseError;

import java.util.HashMap;

public class Metrics {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/metrics.json";

    public Metrics(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public HashMap<String, String> retrieve() throws Exception {
        return this.apiCall.get(RESOURCEPATH);
    }
}
