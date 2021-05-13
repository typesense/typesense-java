package org.typesense.model;

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
}
