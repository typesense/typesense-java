package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.SearchSynonym;
import org.typesense.model.SearchSynonymDeleteResponse;

/**
 * @deprecated This class is deprecated and will be removed in a future version.
 * Use {@link SynonymSet} instead for the new synonym sets API.
 * 
 * Note: The old synonyms API is only available on Typesense v29.0 and below.
 * For Typesense v30.0 and above, use the new synonym sets API.
 */
@Deprecated

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
