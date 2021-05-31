package org.typesense.api.old;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.typesense.api.old.Hit;

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

    @Override
    public String toString() {
        return "SearchResult{" +
                "facetCounts=" + facetCounts +
                ", found=" + found +
                ", tookMs=" + tookMs +
                ", hits=" + hits +
                ", page=" + page +
                ", outOf=" + outOf +
                ", requestParams=" + requestParams +
                ", searchTimeMs=" + searchTimeMs +
                '}';
    }
}
