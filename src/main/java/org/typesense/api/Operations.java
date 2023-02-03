package org.typesense.api;

import java.util.Map;

public class Operations {

    private ApiCall apiCall;
    public static final String RESOUCEPATH = "/operations";

    public Operations(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public Map<String, String> perform(String operationName, Map<String, String> queryParameters) throws Exception {
        return this.apiCall.post(RESOUCEPATH + "/" + operationName, "{}", queryParameters, Map.class);
    }

    public Map<String, String> perform(String operationName) throws Exception {
        return this.apiCall.post(RESOUCEPATH + "/" + operationName, "{}", null, Map.class);
    }
}
