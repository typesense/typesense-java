package org.typesense.api;

import org.typesense.api.exceptions.TypesenseError;
import org.typesense.model.SearchOverride;
import org.typesense.model.SearchOverrideSchema;
import org.typesense.model.SearchOverridesResponse;

public class Overrides {

    public static String RESOURCEPATH = "/overrides";

    private String collectionName;
    private ApiCall apiCall;

    public Overrides(String collectionName, ApiCall apiCall) {
        this.collectionName = collectionName;
        this.apiCall = apiCall;
    }

    public SearchOverride upsert(String overrideId, SearchOverrideSchema searchOverrideSchema) throws Exception {
        return this.apiCall.put(getEndpoint(overrideId), searchOverrideSchema, SearchOverride.class);
    }

    public SearchOverridesResponse retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(null), SearchOverridesResponse.class);
    }

    public String getEndpoint(String operation){
        return Collections.RESOURCE_PATH + "/" + this.collectionName + Overrides.RESOURCEPATH + "/" + (operation==null? "":operation);
    }
}
