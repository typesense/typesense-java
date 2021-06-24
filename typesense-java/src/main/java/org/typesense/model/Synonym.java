package org.typesense.model;

import org.typesense.api.SearchSynonym;

public class Synonym {

    private String collectionName;
    private String synonymId;
    private Api api;

    public Synonym(String collectionName, String synonymId, Api api) {
        this.collectionName = collectionName;
        this.synonymId = synonymId;
        this.api = api;
    }

    public SearchSynonym retrieve(){
        return this.api.get(this.getEndpoint(), SearchSynonym.class);
    }

    public SearchSynonym delete(){
        return this.api.delete(this.getEndpoint(), SearchSynonym.class);
    }

    public String getEndpoint(){
        return Collections.RESOURCE_PATH + "/" + this.collectionName + Synonyms.RESOURCEPATH + "/" + this.synonymId;
    }
}
