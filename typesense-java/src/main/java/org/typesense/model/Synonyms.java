package org.typesense.model;

import org.typesense.api.SearchSynonym;
import org.typesense.api.SearchSynonymSchema;
import org.typesense.api.SearchSynonymsResponse;

public class Synonyms {

    private String collectionName;
    private Api api;
    public final static String RESOURCEPATH = "/synonyms";

    public Synonyms(String collectionName, Api api) {
        this.collectionName = collectionName;
        this.api = api;
    }

    public SearchSynonym upsert(String synonymId, SearchSynonymSchema searchSynonymSchema){
        return this.api.put(getEndpoint(synonymId), searchSynonymSchema,SearchSynonym.class);
    }

    public SearchSynonymsResponse retrieve(){
        return this.api.get(this.getEndpoint(null),SearchSynonymsResponse.class);
    }

    public String getEndpoint(String operation){
        return Collections.RESOURCE_PATH + "/" + this.collectionName + Synonyms.RESOURCEPATH + "/" + (operation==null?"":operation);
    }
}
