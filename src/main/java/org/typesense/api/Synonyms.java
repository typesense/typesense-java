package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.SearchSynonym;
import org.typesense.model.SearchSynonymSchema;
import org.typesense.model.SearchSynonymsResponse;

/**
 * @deprecated This class is deprecated and will be removed in a future version.
 * Use {@link SynonymSets} instead for the new synonym sets API.
 * 
 * Note: The old synonyms API is only available on Typesense v29.0 and below.
 * For Typesense v30.0 and above, use the new synonym sets API.
 */
@Deprecated

public class Synonyms {

    private String collectionName;
    private ApiCall apiCall;
    public final static String RESOURCEPATH = "/synonyms";

    public Synonyms(String collectionName, ApiCall apiCall) {
        this.collectionName = collectionName;
        this.apiCall = apiCall;
    }

    public SearchSynonym upsert(String synonymId, SearchSynonymSchema searchSynonymSchema) throws Exception {
        if (searchSynonymSchema.getRoot() == null) {
            searchSynonymSchema.setRoot("");
        }
        return this.apiCall.put(getEndpoint(synonymId), searchSynonymSchema, null, SearchSynonym.class);
    }

    public SearchSynonymsResponse retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(null), null, SearchSynonymsResponse.class);
    }

    public String getEndpoint(String operation) {
        return Collections.RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(this.collectionName)
                + Synonyms.RESOURCEPATH + "/" + (operation == null ? "" : URLEncoding.encodeURIComponent(operation));
    }
}
