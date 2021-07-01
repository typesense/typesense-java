package org.typesense.model;

import org.typesense.api.CollectionResponse;
import org.typesense.api.CollectionSchema;

public class Collections {

    private final Api api;
    public final static String RESOURCE_PATH = "/collections";

    public Collections(Api api){
        this.api = api;
    }

    public CollectionResponse create(CollectionSchema c){
        return this.api.post(RESOURCE_PATH, c, CollectionResponse.class);
    }

    public CollectionResponse[] retrieve(){
        return this.api.get(RESOURCE_PATH,CollectionResponse[].class);
    }

}
