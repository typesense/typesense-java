package org.typesense.model;

import org.typesense.api.CollectionResponse;

import java.util.HashMap;

public class Collection {

    private final Configuration configuration;

    private final Api api;

    private final String name;

    private Documents documents;
    private HashMap<String, Document> individualDocuments;

    private final String endpoint;

    Collection(String name, Api api, Configuration configuration){
        this.name =name;
        this.api = api;
        this.configuration = configuration;
        this.endpoint = Collections.RESOURCE_PATH + "/" + this.name;
        this.documents = new Documents(this.name, this.api, this.configuration);
        this.individualDocuments = new HashMap<String, Document>();
    }

    public CollectionResponse retrieve(){
        return this.api.get(endpoint, CollectionResponse.class);
    }

    public CollectionResponse delete(){
        return this.api.delete(endpoint,CollectionResponse.class);
    }

    public Documents documents(){
        return this.documents;
    }

    public Document documents(String documentId){
        Document ret_val;

        if(!this.individualDocuments.containsKey(documentId)){
            this.individualDocuments.put(documentId,new Document(this.name, documentId, this.api));
        }

        ret_val = this.individualDocuments.get(documentId);
        return  ret_val;
    }
}
