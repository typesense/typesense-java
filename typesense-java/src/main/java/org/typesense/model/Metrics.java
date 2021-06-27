package org.typesense.model;

import java.util.HashMap;

public class Metrics {

    private Api api;
    public static final String RESOURCEPATH = "/metrics.json";

    public Metrics(Api api) {
        this.api = api;
    }

    public HashMap<String, String> retrieve(){
        return this.api.get(RESOURCEPATH);
    }
}
