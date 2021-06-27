package org.typesense.model;

import java.util.HashMap;

public class Health {

    private Api api;
    public static final String RESOURCEPATH = "/health";

    public Health(Api api) {
        this.api = api;
    }

    public HashMap<String, Object> retrieve(){
        return this.api.get(RESOURCEPATH);
    }
}
