package org.typesense.model;

import org.typesense.api.MultiSearchResponse;

import java.util.HashMap;
import java.util.List;

public class MultiSearch {

    private Api api;
    public static final String RESOURCEPATH = "/multi_search";

    public MultiSearch(Api api) {
        this.api = api;
    }

    public MultiSearchResponse perform(HashMap<String , List<HashMap<String,String>>> multiSearchParameters, HashMap<String, String> common_params){
        return this.api.post(MultiSearch.RESOURCEPATH, multiSearchParameters, common_params, MultiSearchResponse.class);
    }
}
