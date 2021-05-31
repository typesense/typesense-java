package org.typesense.model;

import org.typesense.api.SearchParameters;

import java.util.HashMap;

public class Documents {

    private String collectionName;
    private Api api;
    private  Configuration configuration;

    public static final String RESOURCE_PATH = "/documents";

    Documents(String collectionName, Api api, Configuration configuration) {
        this.collectionName = collectionName;
        this.api = api;
        this.configuration = configuration;
    }

    public HashMap<String, Object> create(HashMap<String, Object> document){
        return this.api.post(getEndPoint("/"), document);
    }

    public org.typesense.api.SearchResult search(SearchParameters searchParameters){
        return this.api.get(getEndPoint("search"), org.typesense.api.SearchResult.class, searchParameters);
    }

    public HashMap<String, Object> delete(HashMap<String, String> queryParameters){
        return this.api.delete(getEndPoint("/"), queryParameters);
    }

    public HashMap<String, Object> export(){
        return  this.api.get(getEndPoint("export"));
    }

    public String getEndPoint(String target){
        return Collections.RESOURCE_PATH + "/" + this.collectionName + Documents.RESOURCE_PATH + "/" + target;
    }

}
