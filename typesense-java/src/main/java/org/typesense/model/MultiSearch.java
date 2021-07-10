package org.typesense.model;

import org.typesense.api.MultiSearchResponse;

import java.util.HashMap;
import java.util.List;

public class MultiSearch {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/multi_search";

    public MultiSearch(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public MultiSearchResponse perform(HashMap<String , List<HashMap<String,String>>> multiSearchParameters, HashMap<String, String> common_params){
        return this.apiCall.post(MultiSearch.RESOURCEPATH, multiSearchParameters, common_params, MultiSearchResponse.class);
    }
}
