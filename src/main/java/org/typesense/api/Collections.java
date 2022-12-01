package org.typesense.api;

import org.typesense.model.CollectionResponse;
import org.typesense.model.CollectionSchema;

public class Collections {

    private final ApiCall apiCall;
    public final static String RESOURCE_PATH = "/collections";

    public Collections(ApiCall apiCall){
        this.apiCall = apiCall;
    }

    public CollectionResponse create(CollectionSchema c) throws Exception {
        return this.apiCall.post(RESOURCE_PATH, c, null, CollectionResponse.class);
    }

    public CollectionResponse[] retrieve() throws Exception {
        return this.apiCall.get(RESOURCE_PATH, null, CollectionResponse[].class);
    }

}
