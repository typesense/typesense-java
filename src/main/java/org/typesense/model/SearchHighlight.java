package org.typesense.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchHighlight   {

    private String field = null;

    /**
     * Present only for (non-array) string fields
     **/
    private String snippet = null;


    /**
     * Present only for (array) string[] fields
     **/
    private List<String> snippets = new ArrayList<String>();

   /**
     * The indices property will be present only for string[] fields and will contain the corresponding indices of the snippets in the search field
     **/
    private List<Integer> indices = new ArrayList<Integer>();

    private List<Object> matchedTokens = new ArrayList<Object>();
    /**
     * Get field
     * @return field
     **/
    @JsonProperty("field")
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public SearchHighlight field(String field) {
        this.field = field;
        return this;
    }

    /**
     * Present only for (non-array) string fields
     * @return snippet
     **/
    @JsonProperty("snippet")
    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public SearchHighlight snippet(String snippet) {
        this.snippet = snippet;
        return this;
    }

    /**
     * Present only for (array) string[] fields
     * @return snippets
     **/
    @JsonProperty("snippets")
    public List<String> getSnippets() {
        return snippets;
    }

    public void setSnippets(List<String> snippets) {
        this.snippets = snippets;
    }

    public SearchHighlight snippets(List<String> snippets) {
        this.snippets = snippets;
        return this;
    }

    public SearchHighlight addSnippetsItem(String snippetsItem) {
        this.snippets.add(snippetsItem);
        return this;
    }

    /**
     * The indices property will be present only for string[] fields and will contain the corresponding indices of the snippets in the search field
     * @return indices
     **/
    @JsonProperty("indices")
    public List<Integer> getIndices() {
        return indices;
    }

    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    public SearchHighlight indices(List<Integer> indices) {
        this.indices = indices;
        return this;
    }

    public SearchHighlight addIndicesItem(Integer indicesItem) {
        this.indices.add(indicesItem);
        return this;
    }

    /**
     * Get matchedTokens
     * @return matchedTokens
     **/
    @JsonProperty("matched_tokens")
    public List<Object> getMatchedTokens() {
        return matchedTokens;
    }

    public void setMatchedTokens(List<Object> matchedTokens) {
        this.matchedTokens = matchedTokens;
    }

    public SearchHighlight matchedTokens(List<Object> matchedTokens) {
        this.matchedTokens = matchedTokens;
        return this;
    }

    public SearchHighlight addMatchedTokensItem(Object matchedTokensItem) {
        this.matchedTokens.add(matchedTokensItem);
        return this;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SearchHighlight {\n");

        sb.append("    field: ").append(toIndentedString(field)).append("\n");
        sb.append("    snippet: ").append(toIndentedString(snippet)).append("\n");
        sb.append("    snippets: ").append(toIndentedString(snippets)).append("\n");
        sb.append("    indices: ").append(toIndentedString(indices)).append("\n");
        sb.append("    matchedTokens: ").append(toIndentedString(matchedTokens)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private static String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}