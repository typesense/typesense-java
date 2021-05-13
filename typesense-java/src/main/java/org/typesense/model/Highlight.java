package org.typesense.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Highlight {

    @JsonProperty("field")
    public String field;

    @JsonProperty("snippet")
    public String snippet;

    @JsonProperty("matched_tokens")
    public List<String> matchedTokens;
}
