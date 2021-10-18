package org.typesense.api;

import org.typesense.model.SearchSynonym;
import org.typesense.model.SearchSynonymSchema;
import org.typesense.model.SearchSynonymsResponse;

public class Synonyms {

    private String collectionName;
    private ApiCall apiCall;
    public final static String RESOURCEPATH = "/synonyms";

    public Synonyms(String collectionName, ApiCall apiCall) {
        this.collectionName = collectionName;
        this.apiCall = apiCall;
    }

    public SearchSynonym upsert(String synonymId, SearchSynonymSchema searchSynonymSchema){
        if (searchSynonymSchema.getRoot() == null) {
            searchSynonymSchema.setRoot("");
        }
        return this.apiCall.put(getEndpoint(synonymId), searchSynonymSchema,SearchSynonym.class);
    }

    public SearchSynonymsResponse retrieve(){
        return this.apiCall.get(this.getEndpoint(null),SearchSynonymsResponse.class);
    }

    public String getEndpoint(String operation){
        return Collections.RESOURCE_PATH + "/" + this.collectionName + Synonyms.RESOURCEPATH + "/" + (operation==null?"":operation);
    }
}
