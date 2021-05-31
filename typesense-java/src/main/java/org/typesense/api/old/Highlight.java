package org.typesense.api.old;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Highlight {

    @JsonProperty("field")
    public String field;

    @JsonProperty("snippet")
    public String snippet;

    @JsonProperty("matched_tokens")
    public List<String> matchedTokens;

    @Override
    public String toString() {
        return "Highlight{" +
                "field='" + field + '\'' +
                ", snippet='" + snippet + '\'' +
                ", matchedTokens=" + matchedTokens +
                '}';
    }
}
