package org.typesense.api;

import org.typesense.model.ApiKey;
import org.typesense.model.ApiKeyDeleteResponse;

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

    public ApiKeyDeleteResponse delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, ApiKeyDeleteResponse.class);
    }

    private String getEndpoint(){
        return Keys.RESOURCEPATH + "/" + this.id;
    }
}
