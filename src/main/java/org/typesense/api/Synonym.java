package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.SearchSynonym;
import org.typesense.model.SearchSynonymDeleteResponse;

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

    public SearchSynonymDeleteResponse delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, SearchSynonymDeleteResponse.class);
    }

    public String getEndpoint() {
        return Collections.RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(this.collectionName)
                + Synonyms.RESOURCEPATH + "/" + URLEncoding.encodeURIComponent(this.synonymId);
    }
}
