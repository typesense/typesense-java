
package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.model.ConversationModelSchema;
import org.typesense.model.ConversationModelUpdateSchema;

public class ConversationModel {

    private final ApiCall apiCall;
    private final String id;

    public ConversationModel(ApiCall apiCall, String id) {
        this.apiCall = apiCall;
        this.id = id;
    }

    public ConversationModelUpdateSchema update(ConversationModelUpdateSchema schema) throws Exception {
        return this.apiCall.put(this.getEndpoint(), schema, null, ConversationModelUpdateSchema.class);
    }

    public ConversationModelSchema retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, ConversationModelSchema.class);
    }
 
    public ConversationModelSchema delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, ConversationModelSchema.class);
    }

    private String getEndpoint() {
        return ConversationModels.RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(this.id);
    }
    
}
