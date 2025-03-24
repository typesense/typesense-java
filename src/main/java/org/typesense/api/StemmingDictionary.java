package org.typesense.api;

import org.typesense.api.utils.URLEncoding;

public class StemmingDictionary {
    private final ApiCall apiCall;
    private final String dictionaryId;

    public StemmingDictionary(String dictionaryId, ApiCall apiCall) {
        this.apiCall = apiCall;
        this.dictionaryId = dictionaryId;
    }


    public org.typesense.model.StemmingDictionary retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, org.typesense.model.StemmingDictionary.class);
    }

    private String getEndpoint() {
        return StemmingDictionaries.RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(this.dictionaryId);
    }
    
}