package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.PresetSchema;
import org.typesense.model.PresetUpsertSchema;
import org.typesense.model.PresetsRetrieveSchema;

public class Presets {
    public final static String RESOURCEPATH = "/presets";

    private final ApiCall apiCall;

    public Presets(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public PresetSchema upsert(String name, PresetUpsertSchema preset) throws Exception {
        return this.apiCall.put(getEndpoint(name), preset, null, PresetSchema.class);
    }

    public PresetsRetrieveSchema retrieve() throws Exception {
        return this.apiCall.get(Presets.RESOURCEPATH, null, PresetsRetrieveSchema.class);
    }

    private String getEndpoint(String name) {
        return RESOURCEPATH + "/" + URLEncoding.encodeURIComponent(name);
    }

}
