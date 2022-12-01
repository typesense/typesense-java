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

    public HashMap<String,Object> retrieve() throws Exception {
        return this.apiCall.get(endpoint, null, HashMap.class);
    }

    public HashMap<String, Object> delete() throws Exception {
        return this.apiCall.delete(this.endpoint, null, HashMap.class);
    }

    public HashMap<String , Object> update(HashMap<String, Object> document) throws Exception {
        return this.apiCall.patch(this.endpoint, document, null, HashMap.class);
    }

}
