
package org.typesense.api;

import org.typesense.api.utils.URLEncoding;
import org.typesense.interfaces.ConversationDeleteSchema;
import org.typesense.interfaces.ConversationSchema;
import org.typesense.model.CollectionUpdateSchema;

public class Conversation {
    private final ApiCall apiCall;
    private final String conversationId;

    public Conversation(String conversationId, ApiCall apiCall) {
        this.apiCall  = apiCall;
        this.conversationId = conversationId;
    }


    public ConversationSchema retrieve() throws Exception {
        return this.apiCall.get(this.getEndpoint(), null, ConversationSchema.class);
    }

    public ConversationDeleteSchema delete() throws Exception {
        return this.apiCall.delete(this.getEndpoint(), null, ConversationDeleteSchema.class);
    }

    public CollectionUpdateSchema update(CollectionUpdateSchema schema) throws Exception {
        return this.apiCall.put(this.getEndpoint(), schema, null, CollectionUpdateSchema.class);
    }

    private String getEndpoint() {
        return Conversations.RESOURCE_PATH + "/" + URLEncoding.encodeURIComponent(conversationId);
    }
    
}
