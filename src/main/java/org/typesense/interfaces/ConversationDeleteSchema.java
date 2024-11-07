package org.typesense.interfaces;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ConversationDeleteSchema {
    @JsonProperty("id")
    private Long id = null;

    public ConversationDeleteSchema id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * The ID of the conversation to delete
     * @return id
    **/
    @Schema(required = true, description = "The ID of the conversation to delete")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationDeleteSchema that = (ConversationDeleteSchema) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ConversationDeleteSchema {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) return "null";
        return o.toString().replace("\n", "\n    ");
    }
}
