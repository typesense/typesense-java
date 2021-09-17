package org.typesense.api;

import org.typesense.model.CollectionResponse;
import org.typesense.model.CollectionSchema;

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
