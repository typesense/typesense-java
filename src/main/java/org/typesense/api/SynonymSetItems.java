package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.SynonymItemSchema;

public class SynonymSetItems {

    private String synonymSetName;
    private ApiCall apiCall;
    public final static String RESOURCEPATH = "/items";

    public SynonymSetItems(String synonymSetName, ApiCall apiCall) {
        this.synonymSetName = synonymSetName;
        this.apiCall = apiCall;
    }

    public SynonymItemSchema[] retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, SynonymItemSchema[].class);
    }

    public String getEndpoint() {
        return SynonymSets.RESOURCEPATH + "/" + URLEncoding.encodeURIComponent(this.synonymSetName)
                + SynonymSetItems.RESOURCEPATH;
    }
}
