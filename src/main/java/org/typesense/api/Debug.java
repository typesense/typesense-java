package org.typesense.api;

import org.typesense.api.exceptions.TypesenseError;

import java.util.HashMap;

public class Debug {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/debug";

    public Debug(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public HashMap<String, String> retrieve() throws Exception {
        return this.apiCall.get(RESOURCEPATH);
    }
}
