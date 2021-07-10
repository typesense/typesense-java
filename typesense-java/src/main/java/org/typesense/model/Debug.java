package org.typesense.model;

import java.util.HashMap;

public class Debug {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/debug";

    public Debug(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public HashMap<String, String> retrieve(){
        return this.apiCall.get(RESOURCEPATH);
    }
}
