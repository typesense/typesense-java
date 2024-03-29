package org.typesense.api;

import org.typesense.model.ApiKey;

public class Key {

    private Long id;
    private ApiCall apiCall;

    public Key(Long id, ApiCall apiCall) {
        this.id = id;
        this.apiCall = apiCall;
    }

    public ApiKey retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, ApiKey.class);
    }

    public ApiKey delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, ApiKey.class);
    }

    private String getEndpoint(){
        return Keys.RESOURCEPATH + "/" + this.id;
    }
}
