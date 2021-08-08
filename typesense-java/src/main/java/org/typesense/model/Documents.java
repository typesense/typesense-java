package org.typesense.model;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.typesense.api.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Documents {

    private String collectionName;
    private ApiCall apiCall;
    private  Configuration configuration;

    public static final String RESOURCE_PATH = "/documents";

    Documents(String collectionName, ApiCall apiCall, Configuration configuration) {
        this.collectionName = collectionName;
        this.apiCall = apiCall;
        this.configuration = configuration;
    }

    public HashMap<String, Object> create(HashMap<String, Object> document){
        return this.apiCall.post(getEndPoint("/"), document);
    }

    public String create(String document){
        return this.apiCall.post(getEndPoint("/"),document,null,String.class);
    }

    public String create(HashMap<String, Object> document, ImportDocumentsParameters queryParameters){
        return this.apiCall.post(getEndPoint("/"),document,queryParameters,String.class);
    }

    public HashMap<String, Object> upsert(HashMap<String, Object> document){
        ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();
        queryParameters.action("upsert");

        return this.apiCall.post(getEndPoint("/"),document,queryParameters,HashMap.class);
    }

    public SearchResult search(SearchParameters searchParameters){
        return this.apiCall.get(getEndPoint("search"),  searchParameters,org.typesense.api.SearchResult.class);
    }

    public HashMap<String, Object> delete(DeleteDocumentsParameters queryParameters){
        return this.apiCall.delete(getEndPoint("/"), queryParameters);
    }

    public String export(){
        return this.apiCall.get(getEndPoint("export"),String.class);
    }

    public String export(ExportDocumentsParameters exportDocumentsParameters){
        return this.apiCall.get(getEndPoint("export"), exportDocumentsParameters, String.class);
    }

    public String import_(String document, ImportDocumentsParameters queryParameters){
            return this.apiCall.post(this.getEndPoint("import"),document, queryParameters,String.class);
    }

    public String import_(ArrayList<HashMap<String, Object>> documents, ImportDocumentsParameters queryParameters){
        ObjectMapper mapper = new ObjectMapper();
        String json="";
        for(int i=0;i<documents.size();i++){
            HashMap<String, Object> document = documents.get(i);
            try {
                //Convert Map to JSON
                json = json.concat(mapper.writeValueAsString(document) + "\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        json = json.trim();
        return this.apiCall.post(this.getEndPoint("import"),json,queryParameters,String.class);
    }

    public String getEndPoint(String target){
        return Collections.RESOURCE_PATH + "/" + this.collectionName + Documents.RESOURCE_PATH + "/" + target;
    }
}
