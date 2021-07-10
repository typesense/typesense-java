package org.typesense.model;

import java.util.HashMap;

public class Operations {

    private ApiCall apiCall;
    public static final String RESOUCEPATH = "/operations";

    public Operations(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public HashMap<String, String> perform(String operationName, HashMap<String, String> queryParameters){
        return this.apiCall.post(RESOUCEPATH + "/" + operationName, queryParameters);
    }

    public HashMap<String, String> perform(String operationName){
        return this.apiCall.post(RESOUCEPATH + "/" + operationName);
    }
}
