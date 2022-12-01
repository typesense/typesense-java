package org.typesense.api;

import org.typesense.model.SearchSynonym;

public class Synonym {

    private String collectionName;
    private String synonymId;
    private ApiCall apiCall;

    public Synonym(String collectionName, String synonymId, ApiCall apiCall) {
        this.collectionName = collectionName;
        this.synonymId = synonymId;
        this.apiCall = apiCall;
    }

    public SearchSynonym retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, SearchSynonym.class);
    }

    public SearchSynonym delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, SearchSynonym.class);
    }

    public String getEndpoint(){
        return Collections.RESOURCE_PATH + "/" + this.collectionName + Synonyms.RESOURCEPATH + "/" + this.synonymId;
    }
}
