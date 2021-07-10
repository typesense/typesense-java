package org.typesense.model;

import org.typesense.api.CollectionResponse;

import java.util.HashMap;

public class Collection {

    private final Configuration configuration;

    private final ApiCall apiCall;

    private final String name;

    private Documents documents;
    private HashMap<String, Document> individualDocuments;

    private Synonyms synonyms;
    private HashMap<String, Synonym> individualSynonyms;

    private Overrides overrides;
    private HashMap<String, Override> individualOverrides;

    private final String endpoint;

    Collection(String name, ApiCall apiCall, Configuration configuration){
        this.name =name;
        this.apiCall = apiCall;
        this.configuration = configuration;
        this.endpoint = Collections.RESOURCE_PATH + "/" + this.name;
        this.documents = new Documents(this.name, this.apiCall, this.configuration);
        this.individualDocuments = new HashMap<String, Document>();
        this.synonyms = new Synonyms(this.name, this.apiCall);
        this.individualSynonyms = new HashMap<String, Synonym>();
        this.overrides = new Overrides(this.name, this.apiCall);
        this.individualOverrides = new HashMap<String, Override>();
    }

    public CollectionResponse retrieve(){
        return this.apiCall.get(endpoint, CollectionResponse.class);
    }

    public CollectionResponse delete(){
        return this.apiCall.delete(endpoint,CollectionResponse.class);
    }

    public Documents documents(){
        return this.documents;
    }

    public Document documents(String documentId){
        Document ret_val;

        if(!this.individualDocuments.containsKey(documentId)){
            this.individualDocuments.put(documentId,new Document(this.name, documentId, this.apiCall));
        }

        ret_val = this.individualDocuments.get(documentId);
        return  ret_val;
    }

    public Synonyms synonyms(){
        return this.synonyms;
    }

    public Synonym synonyms(String synonymId){
        Synonym ret_val;

        if(!this.individualSynonyms.containsKey(synonymId)){
            this.individualSynonyms.put(synonymId, new Synonym(this.name, synonymId, this.apiCall));
        }

        ret_val = this.individualSynonyms.get(synonymId);
        return  ret_val;
    }

    public Overrides overrides(){
        return this.overrides;
    }

    public Override overrides(String overrideId){
        Override ret_val;

        if(!this.individualOverrides.containsKey(overrideId)){
            this.individualOverrides.put(overrideId, new Override(this.name, overrideId, this.apiCall));
        }

        ret_val = this.individualOverrides.get(overrideId);
        return ret_val;
    }
}
