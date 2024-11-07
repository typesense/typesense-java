package org.typesense.interfaces;


import java.util.Arrays;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ConversationSchema {
    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("conversation")
    private Object[] conversation = null;

    @JsonProperty("last_updated")
    private Long lastUpdated = null;

    @JsonProperty("ttl")
    private Long ttl = null;

    public ConversationSchema id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * The unique identifier of the conversation
     * @return id
    **/
    @Schema(required = true, description = "The unique identifier of the conversation")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConversationSchema conversation(Object[] conversation) {
        this.conversation = conversation;
        return this;
    }

    /**
     * Array of conversation objects
     * @return conversation
    **/
    @Schema(required = true, description = "Array of conversation objects")
    public Object[] getConversation() {
        return conversation;
    }

    public void setConversation(Object[] conversation) {
        this.conversation = conversation;
    }

    public ConversationSchema lastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    /**
     * Timestamp of when the conversation was last updated
     * @return lastUpdated
    **/
    @Schema(required = true, description = "Timestamp of when the conversation was last updated")
    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public ConversationSchema ttl(Long ttl) {
        this.ttl = ttl;
        return this;
    }

    /**
     * Time to live for the conversation in seconds
     * @return ttl
    **/
    @Schema(required = true, description = "Time to live for the conversation in seconds")
    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationSchema that = (ConversationSchema) o;
        return Objects.equals(this.id, that.id) &&
               Arrays.equals(this.conversation, that.conversation) &&
               Objects.equals(this.lastUpdated, that.lastUpdated) &&
               Objects.equals(this.ttl, that.ttl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Arrays.hashCode(conversation), lastUpdated, ttl);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ConversationSchema {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    conversation: ").append(toIndentedString(Arrays.toString(conversation))).append("\n");
        sb.append("    lastUpdated: ").append(toIndentedString(lastUpdated)).append("\n");
        sb.append("    ttl: ").append(toIndentedString(ttl)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) return "null";
        return o.toString().replace("\n", "\n    ");
    }
}
