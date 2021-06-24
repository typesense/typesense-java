package org.typesense.model;

import org.typesense.api.SearchOverride;

public class Override {

    private String collectionName;
    private String overrideId;
    private Api api;

    public Override(String collectionName, String overrideId, Api api) {
        this.collectionName = collectionName;
        this.overrideId = overrideId;
        this.api = api;
    }

    public SearchOverride retrieve(){
        return this.api.get(this.getEndpoint(), SearchOverride.class);
    }

    public SearchOverride delete(){
        return this.api.delete(this.getEndpoint(), SearchOverride.class);
    }

    public String getEndpoint(){
        return Collections.RESOURCE_PATH + "/" + this.collectionName + "/" + Overrides.RESOURCEPATH + "/" + this.overrideId;
    }
}
