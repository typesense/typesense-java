package org.typesense.model;

import org.typesense.api.CollectionResponse;
import org.typesense.api.CollectionSchema;

public class Collections {

    private final ApiCall apiCall;
    public final static String RESOURCE_PATH = "/collections";

    public Collections(ApiCall apiCall){
        this.apiCall = apiCall;
    }

    public CollectionResponse create(CollectionSchema c){
        return this.apiCall.post(RESOURCE_PATH, c, CollectionResponse.class);
    }

    public CollectionResponse[] retrieve(){
        return this.apiCall.get(RESOURCE_PATH,CollectionResponse[].class);
    }

}
