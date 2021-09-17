package org.typesense.api;

import java.util.HashMap;

public class Health {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/health";

    public Health(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public HashMap<String, Object> retrieve(){
        return this.apiCall.get(RESOURCEPATH);
    }
}
