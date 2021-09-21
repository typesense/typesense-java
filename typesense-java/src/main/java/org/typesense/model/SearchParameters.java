package org.typesense.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SearchParameters {

  private String q = null;

  private List<String> queryBy = new ArrayList<>();

  private List<String> queryByWeights = new ArrayList<>();

  private OneOfsearchParametersMaxHits maxHits = null;

  private List<Boolean> prefix = new ArrayList<>();

  private String filterBy = null;

  private List<String> sortBy = new ArrayList<>();

  private List<String> facetBy = new ArrayList<>();

  private Integer maxFacetValues = null;

  private String facetQuery = null;

  private Integer numTypos = null;

  private Integer page = null;

  private Integer perPage = null;

  private List<String> groupBy = new ArrayList<>();

  private Integer groupLimit = null;

  private List<String> includeFields = new ArrayList<>();

  private List<String> excludeFields = new ArrayList<>();

  private List<String> highlightFullFields = new ArrayList<>();

  private Integer highlightAffixNumTokens = null;

  private String highlightStartTag = null;

  private String highlightEndTag = null;

  private Integer snippetThreshold = null;

  private Integer dropTokensThreshold = null;

  private Integer typoTokensThreshold = null;

  private List<String> pinnedHits = new ArrayList<>();

  private List<String> hiddenHits = new ArrayList<>();

  private List<String> highlightFields = new ArrayList<>();

  private Boolean preSegmentedQuery = null;

  private Boolean enableOverrides = null;

  private Boolean prioritizeExactMatch = null;
 /**
   * The query text to search for in the collection. Use * as the search string to return all documents. This is typically useful when used in conjunction with filter_by.
   * @return q
  **/
  @JsonProperty("q")
  public String getQ() {
    return q;
  }

  public void setQ(String q) {
    this.q = q;
  }

  public SearchParameters q(String q) {
    this.q = q;
    return this;
  }

 /**
   * A list of &#x60;string&#x60; fields that should be queried against. Multiple fields are separated with a comma.
   * @return queryBy
  **/
  @JsonProperty("query_by")
  public List<String> getQueryBy() {
    return queryBy;
  }

  public void setQueryBy(List<String> queryBy) {
    this.queryBy = queryBy;
  }

  public SearchParameters queryBy(List<String> queryBy) {
    this.queryBy = queryBy;
    return this;
  }

  public SearchParameters addQueryByItem(String queryByItem) {
    this.queryBy.add(queryByItem);
    return this;
  }

 /**
   * The relative weight to give each &#x60;query_by&#x60; field when ranking results. This can be used to boost fields in priority, when looking for matches. Multiple fields are separated with a comma.
   * @return queryByWeights
  **/
  @JsonProperty("query_by_weights")
  public List<String> getQueryByWeights() {
    return queryByWeights;
  }

  public void setQueryByWeights(List<String> queryByWeights) {
    this.queryByWeights = queryByWeights;
  }

  public SearchParameters queryByWeights(List<String> queryByWeights) {
    this.queryByWeights = queryByWeights;
    return this;
  }

  public SearchParameters addQueryByWeightsItem(String queryByWeightsItem) {
    this.queryByWeights.add(queryByWeightsItem);
    return this;
  }

 /**
   * Maximum number of hits returned. Increasing this value might increase search latency. Default: 500. Use &#x60;all&#x60; to return all hits found. 
   * @return maxHits
  **/
  @JsonProperty("max_hits")
  public OneOfsearchParametersMaxHits getMaxHits() {
    return maxHits;
  }

  public void setMaxHits(OneOfsearchParametersMaxHits maxHits) {
    this.maxHits = maxHits;
  }

  public SearchParameters maxHits(OneOfsearchParametersMaxHits maxHits) {
    this.maxHits = maxHits;
    return this;
  }

 /**
   * Boolean field to indicate that the last word in the query should be treated as a prefix, and not as a whole word. This is used for building autocomplete and instant search interfaces. Defaults to true.
   * @return prefix
  **/
  @JsonProperty("prefix")
  public List<Boolean> getPrefix() {
    return prefix;
  }

  public void setPrefix(List<Boolean> prefix) {
    this.prefix = prefix;
  }

  public SearchParameters prefix(List<Boolean> prefix) {
    this.prefix = prefix;
    return this;
  }

  public SearchParameters addPrefixItem(Boolean prefixItem) {
    this.prefix.add(prefixItem);
    return this;
  }

 /**
   * Filter conditions for refining your search results. Separate multiple conditions with &amp;&amp;.
   * @return filterBy
  **/
  @JsonProperty("filter_by")
  public String getFilterBy() {
    return filterBy;
  }

  public void setFilterBy(String filterBy) {
    this.filterBy = filterBy;
  }

  public SearchParameters filterBy(String filterBy) {
    this.filterBy = filterBy;
    return this;
  }

 /**
   * A list of numerical fields and their corresponding sort orders that will be used for ordering your results. Up to 3 sort fields can be specified. The text similarity score is exposed as a special &#x60;_text_match&#x60; field that you can use in the list of sorting fields. If no &#x60;sort_by&#x60; parameter is specified, results are sorted by &#x60;_text_match:desc,default_sorting_field:desc&#x60;
   * @return sortBy
  **/
  @JsonProperty("sort_by")
  public List<String> getSortBy() {
    return sortBy;
  }

  public void setSortBy(List<String> sortBy) {
    this.sortBy = sortBy;
  }

  public SearchParameters sortBy(List<String> sortBy) {
    this.sortBy = sortBy;
    return this;
  }

  public SearchParameters addSortByItem(String sortByItem) {
    this.sortBy.add(sortByItem);
    return this;
  }

 /**
   * A list of fields that will be used for faceting your results on. Separate multiple fields with a comma.
   * @return facetBy
  **/
  @JsonProperty("facet_by")
  public List<String> getFacetBy() {
    return facetBy;
  }

  public void setFacetBy(List<String> facetBy) {
    this.facetBy = facetBy;
  }

  public SearchParameters facetBy(List<String> facetBy) {
    this.facetBy = facetBy;
    return this;
  }

  public SearchParameters addFacetByItem(String facetByItem) {
    this.facetBy.add(facetByItem);
    return this;
  }

 /**
   * Maximum number of facet values to be returned.
   * @return maxFacetValues
  **/
  @JsonProperty("max_facet_values")
  public Integer getMaxFacetValues() {
    return maxFacetValues;
  }

  public void setMaxFacetValues(Integer maxFacetValues) {
    this.maxFacetValues = maxFacetValues;
  }

  public SearchParameters maxFacetValues(Integer maxFacetValues) {
    this.maxFacetValues = maxFacetValues;
    return this;
  }

 /**
   * Facet values that are returned can now be filtered via this parameter. The matching facet text is also highlighted. For example, when faceting by &#x60;category&#x60;, you can set &#x60;facet_query&#x3D;category:shoe&#x60; to return only facet values that contain the prefix \&quot;shoe\&quot;.
   * @return facetQuery
  **/
  @JsonProperty("facet_query")
  public String getFacetQuery() {
    return facetQuery;
  }

  public void setFacetQuery(String facetQuery) {
    this.facetQuery = facetQuery;
  }

  public SearchParameters facetQuery(String facetQuery) {
    this.facetQuery = facetQuery;
    return this;
  }

 /**
   * The number of typographical errors (1 or 2) that would be tolerated. Default: 2 
   * @return numTypos
  **/
  @JsonProperty("num_typos")
  public Integer getNumTypos() {
    return numTypos;
  }

  public void setNumTypos(Integer numTypos) {
    this.numTypos = numTypos;
  }

  public SearchParameters numTypos(Integer numTypos) {
    this.numTypos = numTypos;
    return this;
  }

 /**
   * Results from this specific page number would be fetched.
   * @return page
  **/
  @JsonProperty("page")
  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public SearchParameters page(Integer page) {
    this.page = page;
    return this;
  }

 /**
   * Number of results to fetch per page. Default: 10
   * @return perPage
  **/
  @JsonProperty("per_page")
  public Integer getPerPage() {
    return perPage;
  }

  public void setPerPage(Integer perPage) {
    this.perPage = perPage;
  }

  public SearchParameters perPage(Integer perPage) {
    this.perPage = perPage;
    return this;
  }

 /**
   * You can aggregate search results into groups or buckets by specify one or more &#x60;group_by&#x60; fields. Separate multiple fields with a comma. To group on a particular field, it must be a faceted field.
   * @return groupBy
  **/
  @JsonProperty("group_by")
  public List<String> getGroupBy() {
    return groupBy;
  }

  public void setGroupBy(List<String> groupBy) {
    this.groupBy = groupBy;
  }

  public SearchParameters groupBy(List<String> groupBy) {
    this.groupBy = groupBy;
    return this;
  }

  public SearchParameters addGroupByItem(String groupByItem) {
    this.groupBy.add(groupByItem);
    return this;
  }

 /**
   * Maximum number of hits to be returned for every group. If the &#x60;group_limit&#x60; is set as &#x60;K&#x60; then only the top K hits in each group are returned in the response. Default: 3 
   * @return groupLimit
  **/
  @JsonProperty("group_limit")
  public Integer getGroupLimit() {
    return groupLimit;
  }

  public void setGroupLimit(Integer groupLimit) {
    this.groupLimit = groupLimit;
  }

  public SearchParameters groupLimit(Integer groupLimit) {
    this.groupLimit = groupLimit;
    return this;
  }

 /**
   * List of fields from the document to include in the search result
   * @return includeFields
  **/
  @JsonProperty("include_fields")
  public List<String> getIncludeFields() {
    return includeFields;
  }

  public void setIncludeFields(List<String> includeFields) {
    this.includeFields = includeFields;
  }

  public SearchParameters includeFields(List<String> includeFields) {
    this.includeFields = includeFields;
    return this;
  }

  public SearchParameters addIncludeFieldsItem(String includeFieldsItem) {
    this.includeFields.add(includeFieldsItem);
    return this;
  }

 /**
   * List of fields from the document to exclude in the search result
   * @return excludeFields
  **/
  @JsonProperty("exclude_fields")
  public List<String> getExcludeFields() {
    return excludeFields;
  }

  public void setExcludeFields(List<String> excludeFields) {
    this.excludeFields = excludeFields;
  }

  public SearchParameters excludeFields(List<String> excludeFields) {
    this.excludeFields = excludeFields;
    return this;
  }

  public SearchParameters addExcludeFieldsItem(String excludeFieldsItem) {
    this.excludeFields.add(excludeFieldsItem);
    return this;
  }

 /**
   * List of fields which should be highlighted fully without snippeting
   * @return highlightFullFields
  **/
  @JsonProperty("highlight_full_fields")
  public List<String> getHighlightFullFields() {
    return highlightFullFields;
  }

  public void setHighlightFullFields(List<String> highlightFullFields) {
    this.highlightFullFields = highlightFullFields;
  }

  public SearchParameters highlightFullFields(List<String> highlightFullFields) {
    this.highlightFullFields = highlightFullFields;
    return this;
  }

  public SearchParameters addHighlightFullFieldsItem(String highlightFullFieldsItem) {
    this.highlightFullFields.add(highlightFullFieldsItem);
    return this;
  }

 /**
   * The number of tokens that should surround the highlighted text on each side. Default: 4 
   * @return highlightAffixNumTokens
  **/
  @JsonProperty("highlight_affix_num_tokens")
  public Integer getHighlightAffixNumTokens() {
    return highlightAffixNumTokens;
  }

  public void setHighlightAffixNumTokens(Integer highlightAffixNumTokens) {
    this.highlightAffixNumTokens = highlightAffixNumTokens;
  }

  public SearchParameters highlightAffixNumTokens(Integer highlightAffixNumTokens) {
    this.highlightAffixNumTokens = highlightAffixNumTokens;
    return this;
  }

 /**
   * The start tag used for the highlighted snippets. Default: &#x60;&lt;mark&gt;&#x60; 
   * @return highlightStartTag
  **/
  @JsonProperty("highlight_start_tag")
  public String getHighlightStartTag() {
    return highlightStartTag;
  }

  public void setHighlightStartTag(String highlightStartTag) {
    this.highlightStartTag = highlightStartTag;
  }

  public SearchParameters highlightStartTag(String highlightStartTag) {
    this.highlightStartTag = highlightStartTag;
    return this;
  }

 /**
   * The end tag used for the highlighted snippets. Default: &#x60;&lt;/mark&gt;&#x60; 
   * @return highlightEndTag
  **/
  @JsonProperty("highlight_end_tag")
  public String getHighlightEndTag() {
    return highlightEndTag;
  }

  public void setHighlightEndTag(String highlightEndTag) {
    this.highlightEndTag = highlightEndTag;
  }

  public SearchParameters highlightEndTag(String highlightEndTag) {
    this.highlightEndTag = highlightEndTag;
    return this;
  }

 /**
   * Field values under this length will be fully highlighted, instead of showing a snippet of relevant portion. Default: 30 
   * @return snippetThreshold
  **/
  @JsonProperty("snippet_threshold")
  public Integer getSnippetThreshold() {
    return snippetThreshold;
  }

  public void setSnippetThreshold(Integer snippetThreshold) {
    this.snippetThreshold = snippetThreshold;
  }

  public SearchParameters snippetThreshold(Integer snippetThreshold) {
    this.snippetThreshold = snippetThreshold;
    return this;
  }

 /**
   * If the number of results found for a specific query is less than this number, Typesense will attempt to drop the tokens in the query until enough results are found. Tokens that have the least individual hits are dropped first. Set to 0 to disable. Default: 10 
   * @return dropTokensThreshold
  **/
  @JsonProperty("drop_tokens_threshold")
  public Integer getDropTokensThreshold() {
    return dropTokensThreshold;
  }

  public void setDropTokensThreshold(Integer dropTokensThreshold) {
    this.dropTokensThreshold = dropTokensThreshold;
  }

  public SearchParameters dropTokensThreshold(Integer dropTokensThreshold) {
    this.dropTokensThreshold = dropTokensThreshold;
    return this;
  }

 /**
   * If the number of results found for a specific query is less than this number, Typesense will attempt to look for tokens with more typos until enough results are found. Default: 100 
   * @return typoTokensThreshold
  **/
  @JsonProperty("typo_tokens_threshold")
  public Integer getTypoTokensThreshold() {
    return typoTokensThreshold;
  }

  public void setTypoTokensThreshold(Integer typoTokensThreshold) {
    this.typoTokensThreshold = typoTokensThreshold;
  }

  public SearchParameters typoTokensThreshold(Integer typoTokensThreshold) {
    this.typoTokensThreshold = typoTokensThreshold;
    return this;
  }

 /**
   * A list of records to unconditionally include in the search results at specific positions. An example use case would be to feature or promote certain items on the top of search results. A list of &#x60;record_id:hit_position&#x60;. Eg: to include a record with ID 123 at Position 1 and another record with ID 456 at Position 5, you&#x27;d specify &#x60;123:1,456:5&#x60;. You could also use the Overrides feature to override search results based on rules. Overrides are applied first, followed by &#x60;pinned_hits&#x60; and  finally &#x60;hidden_hits&#x60;. 
   * @return pinnedHits
  **/
  @JsonProperty("pinned_hits")
  public List<String> getPinnedHits() {
    return pinnedHits;
  }

  public void setPinnedHits(List<String> pinnedHits) {
    this.pinnedHits = pinnedHits;
  }

  public SearchParameters pinnedHits(List<String> pinnedHits) {
    this.pinnedHits = pinnedHits;
    return this;
  }

  public SearchParameters addPinnedHitsItem(String pinnedHitsItem) {
    this.pinnedHits.add(pinnedHitsItem);
    return this;
  }

 /**
   * A list of records to unconditionally hide from search results. A list of &#x60;record_id&#x60;s to hide. Eg: to hide records with IDs 123 and 456, you&#x27;d specify &#x60;123,456&#x60;. You could also use the Overrides feature to override search results based on rules. Overrides are applied first, followed by &#x60;pinned_hits&#x60; and finally &#x60;hidden_hits&#x60;. 
   * @return hiddenHits
  **/
  @JsonProperty("hidden_hits")
  public List<String> getHiddenHits() {
    return hiddenHits;
  }

  public void setHiddenHits(List<String> hiddenHits) {
    this.hiddenHits = hiddenHits;
  }

  public SearchParameters hiddenHits(List<String> hiddenHits) {
    this.hiddenHits = hiddenHits;
    return this;
  }

  public SearchParameters addHiddenHitsItem(String hiddenHitsItem) {
    this.hiddenHits.add(hiddenHitsItem);
    return this;
  }

 /**
   * A list of custom fields that must be highlighted even if you don&#x27;t query  for them 
   * @return highlightFields
  **/
  @JsonProperty("highlight_fields")
  public List<String> getHighlightFields() {
    return highlightFields;
  }

  public void setHighlightFields(List<String> highlightFields) {
    this.highlightFields = highlightFields;
  }

  public SearchParameters highlightFields(List<String> highlightFields) {
    this.highlightFields = highlightFields;
    return this;
  }

  public SearchParameters addHighlightFieldsItem(String highlightFieldsItem) {
    this.highlightFields.add(highlightFieldsItem);
    return this;
  }

 /**
   * You can index content from any logographic language into Typesense if you are able to segment / split the text into space-separated words yourself  before indexing and querying. Set this parameter to tre to do the same 
   * @return preSegmentedQuery
  **/
  @JsonProperty("pre_segmented_query")
  public Boolean isPreSegmentedQuery() {
    return preSegmentedQuery;
  }

  public void setPreSegmentedQuery(Boolean preSegmentedQuery) {
    this.preSegmentedQuery = preSegmentedQuery;
  }

  public SearchParameters preSegmentedQuery(Boolean preSegmentedQuery) {
    this.preSegmentedQuery = preSegmentedQuery;
    return this;
  }

 /**
   * If you have some overrides defined but want to disable all of them during query time, you can do that by setting this parameter to false 
   * @return enableOverrides
  **/
  @JsonProperty("enable_overrides")
  public Boolean isEnableOverrides() {
    return enableOverrides;
  }

  public void setEnableOverrides(Boolean enableOverrides) {
    this.enableOverrides = enableOverrides;
  }

  public SearchParameters enableOverrides(Boolean enableOverrides) {
    this.enableOverrides = enableOverrides;
    return this;
  }

 /**
   * Set this parameter to true to ensure that an exact match is ranked above the others  
   * @return prioritizeExactMatch
  **/
  @JsonProperty("prioritize_exact_match")
  public Boolean isPrioritizeExactMatch() {
    return prioritizeExactMatch;
  }

  public void setPrioritizeExactMatch(Boolean prioritizeExactMatch) {
    this.prioritizeExactMatch = prioritizeExactMatch;
  }

  public SearchParameters prioritizeExactMatch(Boolean prioritizeExactMatch) {
    this.prioritizeExactMatch = prioritizeExactMatch;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchParameters {\n");
    
    sb.append("    q: ").append(toIndentedString(q)).append("\n");
    sb.append("    queryBy: ").append(toIndentedString(queryBy)).append("\n");
    sb.append("    queryByWeights: ").append(toIndentedString(queryByWeights)).append("\n");
    sb.append("    maxHits: ").append(toIndentedString(maxHits)).append("\n");
    sb.append("    prefix: ").append(toIndentedString(prefix)).append("\n");
    sb.append("    filterBy: ").append(toIndentedString(filterBy)).append("\n");
    sb.append("    sortBy: ").append(toIndentedString(sortBy)).append("\n");
    sb.append("    facetBy: ").append(toIndentedString(facetBy)).append("\n");
    sb.append("    maxFacetValues: ").append(toIndentedString(maxFacetValues)).append("\n");
    sb.append("    facetQuery: ").append(toIndentedString(facetQuery)).append("\n");
    sb.append("    numTypos: ").append(toIndentedString(numTypos)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    perPage: ").append(toIndentedString(perPage)).append("\n");
    sb.append("    groupBy: ").append(toIndentedString(groupBy)).append("\n");
    sb.append("    groupLimit: ").append(toIndentedString(groupLimit)).append("\n");
    sb.append("    includeFields: ").append(toIndentedString(includeFields)).append("\n");
    sb.append("    excludeFields: ").append(toIndentedString(excludeFields)).append("\n");
    sb.append("    highlightFullFields: ").append(toIndentedString(highlightFullFields)).append("\n");
    sb.append("    highlightAffixNumTokens: ").append(toIndentedString(highlightAffixNumTokens)).append("\n");
    sb.append("    highlightStartTag: ").append(toIndentedString(highlightStartTag)).append("\n");
    sb.append("    highlightEndTag: ").append(toIndentedString(highlightEndTag)).append("\n");
    sb.append("    snippetThreshold: ").append(toIndentedString(snippetThreshold)).append("\n");
    sb.append("    dropTokensThreshold: ").append(toIndentedString(dropTokensThreshold)).append("\n");
    sb.append("    typoTokensThreshold: ").append(toIndentedString(typoTokensThreshold)).append("\n");
    sb.append("    pinnedHits: ").append(toIndentedString(pinnedHits)).append("\n");
    sb.append("    hiddenHits: ").append(toIndentedString(hiddenHits)).append("\n");
    sb.append("    highlightFields: ").append(toIndentedString(highlightFields)).append("\n");
    sb.append("    preSegmentedQuery: ").append(toIndentedString(preSegmentedQuery)).append("\n");
    sb.append("    enableOverrides: ").append(toIndentedString(enableOverrides)).append("\n");
    sb.append("    prioritizeExactMatch: ").append(toIndentedString(prioritizeExactMatch)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
