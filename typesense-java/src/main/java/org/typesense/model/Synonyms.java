package org.typesense.model;

import org.typesense.api.SearchSynonym;
import org.typesense.api.SearchSynonymSchema;
import org.typesense.api.SearchSynonymsResponse;

public class Synonyms {

    private String collectionName;
    private ApiCall apiCall;
    public final static String RESOURCEPATH = "/synonyms";

    public Synonyms(String collectionName, ApiCall apiCall) {
        this.collectionName = collectionName;
        this.apiCall = apiCall;
    }

    public SearchSynonym upsert(String synonymId, SearchSynonymSchema searchSynonymSchema){
        return this.apiCall.put(getEndpoint(synonymId), searchSynonymSchema,SearchSynonym.class);
    }

    public SearchSynonymsResponse retrieve(){
        return this.apiCall.get(this.getEndpoint(null),SearchSynonymsResponse.class);
    }

    public String getEndpoint(String operation){
        return Collections.RESOURCE_PATH + "/" + this.collectionName + Synonyms.RESOURCEPATH + "/" + (operation==null?"":operation);
    }
}
