package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.SynonymItemDeleteSchema;
import org.typesense.model.SynonymItemSchema;
import org.typesense.model.SynonymItemUpsertSchema;

public class SynonymSetItem {

    private String synonymSetName;
    private String itemId;
    private ApiCall apiCall;

    public SynonymSetItem(String synonymSetName, String itemId, ApiCall apiCall) {
        this.synonymSetName = synonymSetName;
        this.itemId = itemId;
        this.apiCall = apiCall;
    }

    public SynonymItemSchema retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, SynonymItemSchema.class);
    }

    public SynonymItemSchema upsert(SynonymItemUpsertSchema synonymItemUpsertSchema) throws Exception {
        return this.apiCall.put(this.getEndpoint(), synonymItemUpsertSchema, null, SynonymItemSchema.class);
    }

    public SynonymItemDeleteSchema delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, SynonymItemDeleteSchema.class);
    }

    public String getEndpoint() {
        return SynonymSets.RESOURCEPATH + "/" + URLEncoding.encodeURIComponent(this.synonymSetName)
                + SynonymSetItems.RESOURCEPATH + "/" + URLEncoding.encodeURIComponent(this.itemId);
    }
}
