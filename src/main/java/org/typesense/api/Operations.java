package org.typesense.api;

import java.util.HashMap;

public class Operations {

    private ApiCall apiCall;
    public static final String RESOUCEPATH = "/operations";

    public Operations(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public HashMap<String, String> perform(String operationName, HashMap<String, String> queryParameters) throws Exception {
        return this.apiCall.post(RESOUCEPATH + "/" + operationName, "{}", queryParameters, HashMap.class);
    }

    public HashMap<String, String> perform(String operationName) throws Exception {
        return this.apiCall.post(RESOUCEPATH + "/" + operationName, "{}", null, HashMap.class);
    }
}
