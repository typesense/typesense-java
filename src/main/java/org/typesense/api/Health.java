package org.typesense.api;

import org.typesense.api.exceptions.TypesenseError;

import java.util.HashMap;

public class Health {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/health";

    public Health(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public HashMap<String, Object> retrieve() throws Exception {
        return this.apiCall.get(RESOURCEPATH);
    }
}
