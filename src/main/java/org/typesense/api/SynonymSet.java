package org.typesense.api;

import java.util.HashMap;
import java.util.Map;

import org.typesense.api.utils.URLEncoding;

import org.typesense.model.SynonymSetCreateSchema;
import org.typesense.model.SynonymSetSchema;
import org.typesense.model.SynonymSetDeleteSchema;

public class SynonymSet {

    private String synonymSetName;
    private ApiCall apiCall;

    private SynonymSetItems items;
    private Map<String, SynonymSetItem> individualItems;

    public SynonymSet(String synonymSetName, ApiCall apiCall) {
        this.synonymSetName = synonymSetName;
        this.apiCall = apiCall;
        this.items = new SynonymSetItems(this.synonymSetName, this.apiCall);
        this.individualItems = new HashMap<>();
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

    public SynonymSetItems items() {
        return this.items;
    }

    public SynonymSetItem items(String itemId) {
        SynonymSetItem retVal;

        if (!this.individualItems.containsKey(itemId)) {
            this.individualItems.put(itemId, new SynonymSetItem(this.synonymSetName, itemId, this.apiCall));
        }

        retVal = this.individualItems.get(itemId);
        return retVal;
    }

    public String getEndpoint() {
        return "/synonym_sets/" + URLEncoding.encodeURIComponent(this.synonymSetName);
    }
} 