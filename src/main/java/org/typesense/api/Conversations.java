package org.typesense.api;

import java.util.HashMap;
import java.util.Map;

import org.typesense.interfaces.ConversationsRetrieveSchema;

public class Conversations {

    private final ApiCall apiCall;
    public final static String RESOURCE_PATH = "/conversations";

    private final ConversationModels conversationModels;
    private final Map<String, ConversationModel> individualConversations;

    public Conversations(ApiCall apiCall) {
        this.apiCall = apiCall;
        this.conversationModels = new ConversationModels(this.apiCall);
        this.individualConversations = new HashMap<>();
    }

    public ConversationsRetrieveSchema retrieve() throws Exception {
        return this.apiCall.get(Conversations.RESOURCE_PATH, null, ConversationsRetrieveSchema.class);
    } 

    public ConversationModels models() {
        return this.conversationModels;
    }

    public ConversationModel models(String conversationId) {
        ConversationModel retVal;

        if (!this.individualConversations.containsKey(conversationId)) {
            this.individualConversations.put(conversationId, new ConversationModel(apiCall, conversationId));
        }

        retVal = this.individualConversations.get(conversationId);
        return retVal;
    }
}
