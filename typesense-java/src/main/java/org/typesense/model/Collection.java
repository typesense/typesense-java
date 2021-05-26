package org.typesense.model;

import org.typesense.api.CollectionResponse;

public class Collection {

    private final Configuration configuration;

    private final Api api;

    private final String name;

    private final String endpoint;

    Collection(String name, Api api, Configuration configuration){
        this.name =name;
        this.api = api;
        this.configuration = configuration;
        this.endpoint = Collections.RESOURCE_PATH + this.name;
    }

    public CollectionResponse retrieve(){
        return this.api.get(endpoint, CollectionResponse.class);
    }

    public CollectionResponse delete(){
        return this.api.delete(endpoint,CollectionResponse.class);
    }
}
