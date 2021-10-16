package org.typesense.api;

import java.util.HashMap;

public class Document {
    private String collectionName;
    private String documentId;
    private ApiCall apiCall;
    private final String endpoint;

    Document(String collectionName, String documentId, ApiCall apiCall) {
        this.collectionName = collectionName;
        this.documentId = documentId;
        this.apiCall = apiCall;

        this.endpoint = Collections.RESOURCE_PATH + "/" +  this.collectionName + Documents.RESOURCE_PATH + "/" + this.documentId;
    }

    HashMap<String,Object> retrieve(){
        return this.apiCall.get(endpoint);
    }

    HashMap<String, Object> delete(){
        return this.apiCall.delete(this.endpoint);
    }

    HashMap<String , Object> update(HashMap<String, Object> document){
        return this.apiCall.patch(this.endpoint, document);
    }

}
