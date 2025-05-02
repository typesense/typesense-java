package org.typesense.interfaces;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ConversationsRetrieveSchema {
    @JsonProperty("conversations")
    private ConversationSchema[] conversations = null;

    public ConversationsRetrieveSchema conversations(ConversationSchema[] conversations) {
        this.conversations = conversations;
        return this;
    }

    /**
     * Array of conversations
     * @return conversations
    **/
    @Schema(required = true, description = "Array of conversations")
    public ConversationSchema[] getConversations() {
        return conversations;
    }

    public void setConversations(ConversationSchema[] conversations) {
        this.conversations = conversations;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationsRetrieveSchema that = (ConversationsRetrieveSchema) o;
        return Arrays.equals(this.conversations, that.conversations);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(conversations);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ConversationsRetrieveSchema {\n");
        sb.append("    conversations: ").append(toIndentedString(Arrays.toString(conversations))).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) return "null";
        return o.toString().replace("\n", "\n    ");
    }
}