package org.typesense.api;

import org.typesense.api.utils.URLEncoding;

import org.typesense.model.SynonymSetCreateSchema;
import org.typesense.model.SynonymSetSchema;
import org.typesense.model.SynonymSetDeleteSchema;

public class SynonymSet {

    private String synonymSetName;
    private ApiCall apiCall;

    public SynonymSet(String synonymSetName, ApiCall apiCall) {
        this.synonymSetName = synonymSetName;
        this.apiCall = apiCall;
    }

    public SynonymSetCreateSchema retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, SynonymSetCreateSchema.class);
    }

    public SynonymSetSchema upsert(SynonymSetCreateSchema synonymSetCreateSchema) throws Exception {
        return this.apiCall.put(this.getEndpoint(), synonymSetCreateSchema, null, SynonymSetSchema.class);
    }

    public SynonymSetDeleteSchema delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, SynonymSetDeleteSchema.class);
    }

    public String getEndpoint() {
        return "/synonym_sets/" + URLEncoding.encodeURIComponent(this.synonymSetName);
    }
} 