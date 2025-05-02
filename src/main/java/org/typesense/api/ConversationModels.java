package org.typesense.api;

import org.typesense.model.ConversationModelCreateSchema;
import org.typesense.model.ConversationModelSchema;

public class ConversationModels {

    private final ApiCall apiCall;
    public final static String RESOURCE_PATH = "/conversations/models";

    public ConversationModels(ApiCall apiCall) {
        this.apiCall = apiCall;
    }

    public ConversationModelCreateSchema create(ConversationModelCreateSchema schema) throws Exception {
        return this.apiCall.post(RESOURCE_PATH, schema, null, ConversationModelCreateSchema.class);
    }

    public ConversationModelSchema[] retrieve() throws Exception {
        return this.apiCall.get(RESOURCE_PATH, null, ConversationModelSchema[].class);
    }
    
}
