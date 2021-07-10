package org.typesense.model;

import java.util.HashMap;

public class Metrics {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/metrics.json";

    public Metrics(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public HashMap<String, String> retrieve(){
        return this.apiCall.get(RESOURCEPATH);
    }
}
