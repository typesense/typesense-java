package org.typesense.api;

import org.typesense.model.CollectionResponse;
import org.typesense.model.CollectionUpdateSchema;

import java.util.HashMap;
import java.util.Map;

public class Collection {

    private final Configuration configuration;

    private final ApiCall apiCall;

    private final String name;

    private Documents documents;
    private Map<String, Document> individualDocuments;

    private Synonyms synonyms;
    private Map<String, Synonym> individualSynonyms;

    private Overrides overrides;
    private Map<String, Override> individualOverrides;

    private final String endpoint;

    Collection(String name, ApiCall apiCall, Configuration configuration){
        this.name =name;
        this.apiCall = apiCall;
        this.configuration = configuration;
        this.endpoint = Collections.RESOURCE_PATH + "/" + this.name;
        this.documents = new Documents(this.name, this.apiCall, this.configuration);
        this.individualDocuments = new HashMap<>();
        this.synonyms = new Synonyms(this.name, this.apiCall);
        this.individualSynonyms = new HashMap<>();
        this.overrides = new Overrides(this.name, this.apiCall);
        this.individualOverrides = new HashMap<>();
    }

    public CollectionResponse retrieve() throws Exception {
        return this.apiCall.get(endpoint, null, CollectionResponse.class);
    }

    public CollectionUpdateSchema update(CollectionUpdateSchema c) throws Exception {
        return this.apiCall.patch(endpoint, c, null, CollectionUpdateSchema.class);
    }

    public CollectionResponse delete() throws Exception {
        return this.apiCall.delete(endpoint, null, CollectionResponse.class);
    }

    public Documents documents(){
        return this.documents;
    }

    public Document documents(String documentId){
        Document retVal;

        if(!this.individualDocuments.containsKey(documentId)){
            this.individualDocuments.put(documentId,new Document(this.name, documentId, this.apiCall));
        }

        retVal = this.individualDocuments.get(documentId);
        return  retVal;
    }

    public Synonyms synonyms(){
        return this.synonyms;
    }

    public Synonym synonyms(String synonymId){
        Synonym retVal;

        if(!this.individualSynonyms.containsKey(synonymId)){
            this.individualSynonyms.put(synonymId, new Synonym(this.name, synonymId, this.apiCall));
        }

        retVal = this.individualSynonyms.get(synonymId);
        return  retVal;
    }

    public Overrides overrides(){
        return this.overrides;
    }

    public Override overrides(String overrideId){
        Override retVal;

        if(!this.individualOverrides.containsKey(overrideId)){
            this.individualOverrides.put(overrideId, new Override(this.name, overrideId, this.apiCall));
        }

        retVal = this.individualOverrides.get(overrideId);
        return retVal;
    }
}
