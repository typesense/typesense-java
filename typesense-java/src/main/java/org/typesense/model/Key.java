package org.typesense.model;

import org.typesense.api.ApiKey;

public class Key {

    private String id;
    private Api api;

    public Key(String id, Api api) {
        this.id = id;
        this.api = api;
    }

    public ApiKey retrieve(){
        return this.api.get(this.getEndpoint(), ApiKey.class);
    }

    public ApiKey delete(){
        return this.api.delete(this.getEndpoint(), ApiKey.class);
    }

    private String getEndpoint(){
        return Keys.RESOURCEPATH + "/" + this.id;
    }
}
