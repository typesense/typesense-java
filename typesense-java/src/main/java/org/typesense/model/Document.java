package org.typesense.model;

import java.util.HashMap;

public class Document {
    private String collectionName;
    private String documentId;
    private Api api;
    private final String endpoint;

    Document(String collectionName, String documentId, Api api) {
        this.collectionName = collectionName;
        this.documentId = documentId;
        this.api = api;

        this.endpoint = Collections.RESOURCE_PATH + "/" +  this.collectionName + Documents.RESOURCE_PATH + "/" + this.documentId;
    }

    HashMap<String,Object> retrieve(){
        return this.api.get(endpoint);
    }

    HashMap<String, Object> delete(){
        return this.api.delete(this.endpoint);
    }

    HashMap<String , Object> update(HashMap<String, Object> document){
        return this.api.post(this.endpoint, document);
    }

}
