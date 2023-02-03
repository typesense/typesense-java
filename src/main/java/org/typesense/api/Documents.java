package org.typesense.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.typesense.model.*;

import java.util.Map;

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

    public Map<String, Object> create(Map<String, Object> document) throws Exception {
        return this.apiCall.post(getEndPoint("/"), document, null, Map.class);
    }

    public String create(String document) throws Exception {
        return this.apiCall.post(getEndPoint("/"),document,null,String.class);
    }

    public String create(Map<String, Object> document, ImportDocumentsParameters queryParameters) throws Exception {
        return this.apiCall.post(getEndPoint("/"),document,queryParameters,String.class);
    }

    public Map<String, Object> upsert(Map<String, Object> document) throws Exception {
        ImportDocumentsParameters queryParameters = new ImportDocumentsParameters();
        queryParameters.action("upsert");

        return this.apiCall.post(getEndPoint("/"),document,queryParameters,Map.class);
    }

    public SearchResult search(SearchParameters searchParameters) throws Exception {
        return this.apiCall.get(getEndPoint("search"),  searchParameters,org.typesense.model.SearchResult.class);
    }

    public Map<String, Object> delete(DeleteDocumentsParameters queryParameters) throws Exception {
        return this.apiCall.delete(getEndPoint("/"), queryParameters, Map.class);
    }

    public String export() throws Exception {
        return this.apiCall.get(getEndPoint("export"), null, String.class);
    }

    public String export(ExportDocumentsParameters exportDocumentsParameters) throws Exception {
        return this.apiCall.get(getEndPoint("export"), exportDocumentsParameters, String.class);
    }

    public String import_(String document, ImportDocumentsParameters queryParameters) throws Exception {
            return this.apiCall.post(this.getEndPoint("import"),document, queryParameters,String.class);
    }

    public String import_(java.util.Collection<Map<String, Object>> documents, ImportDocumentsParameters queryParameters) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json="";
        for(Map<String, Object> document : documents){
            try {
                //Convert Map to JSON
                json = json.concat(mapper.writeValueAsString(document) + "\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        json = json.trim();
        return this.apiCall.post(this.getEndPoint("import"),json,queryParameters, String.class);
    }

    public String getEndPoint(String target){
        return Collections.RESOURCE_PATH + "/" + this.collectionName + Documents.RESOURCE_PATH + "/" + target;
    }
}
