package org.typesense.model;

import org.typesense.api.SearchOverride;
import org.typesense.api.SearchOverrideSchema;
import org.typesense.api.SearchOverridesResponse;

public class Overrides {

    public static String RESOURCEPATH = "/overrides";

    private String collectionName;
    private Api api;

    public Overrides(String collectionName, Api api) {
        this.collectionName = collectionName;
        this.api = api;
    }

    public SearchOverride upsert(String overrideId, SearchOverrideSchema searchOverrideSchema){
        return this.api.put(getEndpoint(overrideId), searchOverrideSchema, SearchOverride.class);
    }

    public SearchOverridesResponse retrieve(){
        return this.api.get(this.getEndpoint(null), SearchOverridesResponse.class);
    }

    public String getEndpoint(String operation){
        return Collections.RESOURCE_PATH + "/" + this.collectionName + Overrides.RESOURCEPATH + "/" + (operation==null? "":operation);
    }
}
