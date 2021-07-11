package org.typesense.api.old;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class Hit {

    @JsonProperty("highlights")
    public List<Highlight> highlights;

    @JsonProperty("document")
    public JsonNode document;

    @JsonProperty("text_match")
    public String textMatch;

    @Override
    public String toString() {
        return "Hit{" +
                "highlights=" + highlights +
                ", document=" + document +
                ", textMatch='" + textMatch + '\'' +
                '}';
    }
}
