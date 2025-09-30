package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.CurationSetCreateSchema;
import org.typesense.model.CurationSetSchema;
import org.typesense.model.CurationSetDeleteSchema;

public class CurationSet {

    private String curationSetName;
    private ApiCall apiCall;

    public CurationSet(String curationSetName, ApiCall apiCall) {
        this.curationSetName = curationSetName;
        this.apiCall = apiCall;
    }

    public CurationSetCreateSchema retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, CurationSetCreateSchema.class);
    }

    public CurationSetSchema upsert(CurationSetCreateSchema curationSetCreateSchema) throws Exception {
        return this.apiCall.put(this.getEndpoint(), curationSetCreateSchema, null, CurationSetSchema.class);
    }

    public CurationSetDeleteSchema delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, CurationSetDeleteSchema.class);
    }

    public String getEndpoint() {
        return "/curation_sets/" + URLEncoding.encodeURIComponent(this.curationSetName);
    }
}
