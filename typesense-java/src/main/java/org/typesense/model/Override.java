package org.typesense.model;

import org.typesense.api.SearchOverride;

public class Override {

    private String collectionName;
    private String overrideId;
    private ApiCall apiCall;

    public Override(String collectionName, String overrideId, ApiCall apiCall) {
        this.collectionName = collectionName;
        this.overrideId = overrideId;
        this.apiCall = apiCall;
    }

    public SearchOverride retrieve(){
        return this.apiCall.get(this.getEndpoint(), SearchOverride.class);
    }

    public SearchOverride delete(){
        return this.apiCall.delete(this.getEndpoint(), SearchOverride.class);
    }

    public String getEndpoint(){
        return Collections.RESOURCE_PATH + "/" + this.collectionName + "/" + Overrides.RESOURCEPATH + "/" + this.overrideId;
    }
}
