package org.typesense.model;

import org.typesense.api.ApiKey;
import org.typesense.api.ApiKeySchema;
import org.typesense.api.ApiKeysResponse;

public class Keys {

    public static final String RESOURCEPATH = "/keys";
    private Api api;

    public Keys(Api api) {
        this.api = api;
    }

    public ApiKey create(ApiKeySchema apiKeySchema){
        return this.api.post(Keys.RESOURCEPATH, apiKeySchema, ApiKey.class);
    }

    public ApiKeysResponse retrieve(){
        return this.api.get(Keys.RESOURCEPATH, ApiKeysResponse.class);
    }
}
