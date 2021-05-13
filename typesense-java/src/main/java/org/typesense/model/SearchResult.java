package org.typesense.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class SearchResult {

    @JsonProperty("facet_counts")
    public List<Integer> facetCounts;

    @JsonProperty("found")
    public int found;

    @JsonProperty("took_ms")
    public int tookMs;

    @JsonProperty("hits")
    public List<Hit> hits;

    @JsonProperty("page")
    public int page;

    @JsonProperty("out_of")
    public int outOf;

    @JsonProperty("request_params")
    public Map<String, String> requestParams;

    @JsonProperty("search_time_ms")
    public int searchTimeMs;
}
