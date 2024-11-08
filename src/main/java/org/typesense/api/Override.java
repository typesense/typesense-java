package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.SearchOverride;
import org.typesense.model.SearchOverrideDeleteResponse;

public class Override {

    private String collectionName;
    private String overrideId;
    private ApiCall apiCall;

    public Override(String collectionName, String overrideId, ApiCall apiCall) {
        this.collectionName = collectionName;
        this.overrideId = overrideId;
        this.apiCall = apiCall;
    }

    public SearchOverride retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, SearchOverride.class);
    }

    public SearchOverrideDeleteResponse delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, SearchOverrideDeleteResponse.class);
    }

    public String getEndpoint() {
        return Collections.RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(this.collectionName) + "/"
                + Overrides.RESOURCEPATH + "/" + URLEncoding.encodeURIComponent(this.overrideId);
    }
}
