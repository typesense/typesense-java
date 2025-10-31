package org.typesense.api;

import org.typesense.model.CurationSetCreateSchema;
import org.typesense.model.CurationSetSchema;

public class CurationSets {

    private ApiCall apiCall;
    public final static String RESOURCEPATH = "/curation_sets";

    public CurationSets(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public CurationSetSchema upsert(String curationSetName, CurationSetCreateSchema curationSetCreateSchema) throws Exception {
        return this.apiCall.put(getEndpoint(curationSetName), curationSetCreateSchema, null, CurationSetSchema.class);
    }

    public CurationSetSchema[] retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(null), null, CurationSetSchema[].class);
    }

    public String getEndpoint(String operation) {
        return RESOURCEPATH + "/" + (operation == null ? "" : operation);
    }
}
