package org.typesense.api;

import java.util.Map;

public class Debug {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/debug";

    public Debug(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public Map<String, Object> retrieve() throws Exception {
        return this.apiCall.get(RESOURCEPATH, null, Map.class);
    }
}
