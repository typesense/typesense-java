package org.typesense.api;

import java.util.List;
import org.typesense.model.SynonymSetCreateSchema;
import org.typesense.model.SynonymSetSchema;

public class SynonymSets {

    private ApiCall apiCall;
    public final static String RESOURCEPATH = "/synonym_sets";

    public SynonymSets(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public SynonymSetSchema upsert(String synonymSetName, SynonymSetCreateSchema synonymSetCreateSchema) throws Exception {
        return this.apiCall.put(getEndpoint(synonymSetName), synonymSetCreateSchema, null, SynonymSetSchema.class);
    }

    public SynonymSetSchema[] retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(null), null, SynonymSetSchema[].class);
    }

    public String getEndpoint(String operation) {
        return RESOURCEPATH + "/" + (operation == null ? "" : operation);
    }
} 