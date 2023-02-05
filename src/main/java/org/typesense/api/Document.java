package org.typesense.api;

import java.util.Map;

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

    public Map<String,Object> retrieve() throws Exception {
        return this.apiCall.get(endpoint, null, Map.class);
    }

    public Map<String, Object> delete() throws Exception {
        return this.apiCall.delete(this.endpoint, null, Map.class);
    }

    public Map<String , Object> update(Map<String, Object> document) throws Exception {
        return this.apiCall.patch(this.endpoint, document, null, Map.class);
    }

}
