package org.typesense.interfaces;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ApiKeyDeleteResponse {
    @JsonProperty("id")
    private Long id = null;

    public ApiKeyDeleteResponse id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * The unique identifier of the deleted key
     * @return id
    **/
    @Schema(required = true, description = "The unique identifier of the deleted key")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiKeyDeleteResponse that = (ApiKeyDeleteResponse) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ApiKeyDeleteResponse {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) return "null";
        return o.toString().replace("\n", "\n    ");
    }
}
