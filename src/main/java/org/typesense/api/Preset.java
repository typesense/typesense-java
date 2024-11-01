package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.PresetDeleteSchema;
import org.typesense.model.PresetSchema;

public class Preset {
    private final ApiCall apiCall;
    private final String name;

    public Preset(String name, ApiCall apiCall) {
        this.name = name;
        this.apiCall = apiCall;
    }

    public PresetSchema retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, PresetSchema.class);
    }

    public PresetDeleteSchema delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, PresetDeleteSchema.class);
    }

    private String getEndpoint() {
        return Presets.RESOURCEPATH + "/" + URLEncoding.encodeURIComponent(this.name);
    }

}
