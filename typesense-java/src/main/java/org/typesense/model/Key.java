package org.typesense.model;

import org.typesense.api.ApiKey;

public class Key {

    private String id;
    private ApiCall apiCall;

    public Key(String id, ApiCall apiCall) {
        this.id = id;
        this.apiCall = apiCall;
    }

    public ApiKey retrieve(){
        return this.apiCall.get(this.getEndpoint(), ApiKey.class);
    }

    public ApiKey delete(){
        return this.apiCall.delete(this.getEndpoint(), ApiKey.class);
    }

    private String getEndpoint(){
        return Keys.RESOURCEPATH + "/" + this.id;
    }
}
