package org.typesense.interfaces;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ConversationUpdateSchema {
    @JsonProperty("ttl")
    private Long ttl = null;

    public ConversationUpdateSchema ttl(Long ttl) {
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
        ConversationUpdateSchema that = (ConversationUpdateSchema) o;
        return Objects.equals(this.ttl, that.ttl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ttl);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ConversationUpdateSchema {\n");
        sb.append("    ttl: ").append(toIndentedString(ttl)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) return "null";
        return o.toString().replace("\n", "\n    ");
    }
}
