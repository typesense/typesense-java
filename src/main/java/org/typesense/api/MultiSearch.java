package org.typesense.api;

import org.typesense.model.MultiSearchResult;
import org.typesense.model.MultiSearchSearchesParameter;

import java.util.Map;

public class MultiSearch {

    private ApiCall apiCall;
    public static final String RESOURCEPATH = "/multi_search";

    public MultiSearch(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public MultiSearchResult perform(MultiSearchSearchesParameter multiSearchParameters,
                                     Map<String, String> common_params) throws Exception {
        return this.apiCall.post(MultiSearch.RESOURCEPATH, multiSearchParameters, common_params, MultiSearchResult.class);
    }
}
