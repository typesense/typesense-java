package org.typesense.api;

import java.util.HashMap;
import java.util.Map;

public class SearchParameters {

    private Map<String, String> parameters = new HashMap<>();

    public Map<String, String> getParameters() {
        return parameters;
    }

    public SearchParameters setParameter(String key, String value) {
        parameters.put(key, value);
        return this;
    }

    public SearchParameters query(String query) {
        parameters.put("q", query);
        return this;
    }

    public SearchParameters queryBy(String queryBy) {
        parameters.put("query_by", queryBy);
        return this;
    }

    public SearchParameters sortBy(String sortBy) {
        parameters.put("sort_by", sortBy);
        return this;
    }

    public SearchParameters maxHits(String maxHits) {
        parameters.put("max_hits", maxHits);
        return this;
    }

    public SearchParameters prefix(String prefix) {
        parameters.put("prefix", prefix);
        return this;
    }

    public SearchParameters filterBy(String filterBy) {
        parameters.put("filter_by", filterBy);
        return this;
    }

    public SearchParameters facetBy(String facetBy) {
        parameters.put("facet_by", facetBy);
        return this;
    }

    public SearchParameters maxFacetValues(int maxFacetValues) {
        parameters.put("max_facet_values", Integer.toString(maxFacetValues));
        return this;
    }

    public SearchParameters facetQuery(String facetQuery) {
        parameters.put("facet_query", facetQuery);
        return this;
    }

    public SearchParameters numberOfTypos(int numberOfTypos) {
        parameters.put("num_typos", Integer.toString(numberOfTypos));
        return this;
    }

    public SearchParameters page(int page) {
        parameters.put("page", Integer.toString(page));
        return this;
    }

    public SearchParameters perPage(int perPage) {
        parameters.put("per_page", Integer.toString(perPage));
        return this;
    }

    public SearchParameters groupBy(String groupBy) {
        parameters.put("group_by", groupBy);
        return this;
    }

    public SearchParameters groupLimit(int groupLimit) {
        parameters.put("group_limit", Integer.toString(groupLimit));
        return this;
    }

    public SearchParameters includeFields(String includeFields) {
        parameters.put("include_fields", includeFields);
        return this;
    }

    public SearchParameters excludeFields(String excludeFields) {
        parameters.put("exclude_fields", excludeFields);
        return this;
    }

    public SearchParameters highlightFullFields(String highlightFullFields) {
        parameters.put("highlight_full_fields", highlightFullFields);
        return this;
    }

    public SearchParameters highlightAffixNumberOfTokens(int highlightAffixNumberOfTokens) {
        parameters.put("highlight_affix_num_tokens", Integer.toString(highlightAffixNumberOfTokens));
        return this;
    }

    public SearchParameters highlightStartTag(String highlightStartTag) {
        parameters.put("highlight_start_tag", highlightStartTag);
        return this;
    }

    public SearchParameters highlightEndTag(String highlightEndTag) {
        parameters.put("highlight_end_tag", highlightEndTag);
        return this;
    }

    public SearchParameters snippetThreshold(int snippetThreshold) {
        parameters.put("snippet_threshold", Integer.toString(snippetThreshold));
        return this;
    }

    public SearchParameters dropTokensThreshold(int dropTokensThreshold) {
        parameters.put("drop_tokens_threshold", Integer.toString(dropTokensThreshold));
        return this;
    }

    public SearchParameters typoTokensThreshold(int typoTokensThreshold) {
        parameters.put("typo_tokens_threshold", Integer.toString(typoTokensThreshold));
        return this;
    }

    public SearchParameters pinnedHits(String pinnedHits) {
        parameters.put("pinned_hits", pinnedHits);
        return this;
    }

    public SearchParameters hiddenHits(String hiddenHits) {
        parameters.put("hidden_hits", hiddenHits);
        return this;
    }

    public SearchParameters limitHits(int limitHits) {
        parameters.put("limit_hits", Integer.toString(limitHits));
        return this;
    }
}
