package org.typesense.model;

import java.util.HashMap;

public class Operations {

    private Api api;
    public static final String RESOUCEPATH = "/operations";

    public Operations(Api api) {
        this.api = api;
    }

    public HashMap<String, String> perform(String operationName, HashMap<String, String> queryParameters){
        return this.api.post(RESOUCEPATH + "/" + operationName, queryParameters);
    }

    public HashMap<String, String> perform(String operationName){
        return this.api.post(RESOUCEPATH + "/" + operationName);
    }
}
