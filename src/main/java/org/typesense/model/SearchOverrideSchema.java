package org.typesense.model;

import java.util.ArrayList;
import java.util.List;
import org.typesense.model.SearchOverrideExclude;
import org.typesense.model.SearchOverrideInclude;
import org.typesense.model.SearchOverrideRule;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

public class SearchOverrideSchema   {
  
  @Schema(required = true, description = "")
  private SearchOverrideRule rule = null;
  
  @Schema(description = "List of document `id`s that should be included in the search results with their corresponding `position`s.")
 /**
   * List of document `id`s that should be included in the search results with their corresponding `position`s.  
  **/
  private List<SearchOverrideInclude> includes = null;
  
  @Schema(description = "List of document `id`s that should be excluded from the search results.")
 /**
   * List of document `id`s that should be excluded from the search results.  
  **/
  private List<SearchOverrideExclude> excludes = null;
  
  @Schema(description = "A filter by clause that is applied to any search query that matches the override rule. ")
 /**
   * A filter by clause that is applied to any search query that matches the override rule.   
  **/
  private String filterBy = null;
  
  @Schema(description = "Indicates whether search query tokens that exist in the override's rule should be removed from the search query. ")
 /**
   * Indicates whether search query tokens that exist in the override's rule should be removed from the search query.   
  **/
  private Boolean removeMatchedTokens = null;
 /**
   * Get rule
   * @return rule
  **/
  @JsonProperty("rule")
  public SearchOverrideRule getRule() {
    return rule;
  }

  public void setRule(SearchOverrideRule rule) {
    this.rule = rule;
  }

  public SearchOverrideSchema rule(SearchOverrideRule rule) {
    this.rule = rule;
    return this;
  }

 /**
   * List of document &#x60;id&#x60;s that should be included in the search results with their corresponding &#x60;position&#x60;s.
   * @return includes
  **/
  @JsonProperty("includes")
  public List<SearchOverrideInclude> getIncludes() {
    return includes;
  }

  public void setIncludes(List<SearchOverrideInclude> includes) {
    this.includes = includes;
  }

  public SearchOverrideSchema includes(List<SearchOverrideInclude> includes) {
    this.includes = includes;
    return this;
  }

  public SearchOverrideSchema addIncludesItem(SearchOverrideInclude includesItem) {
    this.includes.add(includesItem);
    return this;
  }

 /**
   * List of document &#x60;id&#x60;s that should be excluded from the search results.
   * @return excludes
  **/
  @JsonProperty("excludes")
  public List<SearchOverrideExclude> getExcludes() {
    return excludes;
  }

  public void setExcludes(List<SearchOverrideExclude> excludes) {
    this.excludes = excludes;
  }

  public SearchOverrideSchema excludes(List<SearchOverrideExclude> excludes) {
    this.excludes = excludes;
    return this;
  }

  public SearchOverrideSchema addExcludesItem(SearchOverrideExclude excludesItem) {
    this.excludes.add(excludesItem);
    return this;
  }

 /**
   * A filter by clause that is applied to any search query that matches the override rule. 
   * @return filterBy
  **/
  @JsonProperty("filter_by")
  public String getFilterBy() {
    return filterBy;
  }

  public void setFilterBy(String filterBy) {
    this.filterBy = filterBy;
  }

  public SearchOverrideSchema filterBy(String filterBy) {
    this.filterBy = filterBy;
    return this;
  }

 /**
   * Indicates whether search query tokens that exist in the override&#x27;s rule should be removed from the search query. 
   * @return removeMatchedTokens
  **/
  @JsonProperty("remove_matched_tokens")
  public Boolean isRemoveMatchedTokens() {
    return removeMatchedTokens;
  }

  public void setRemoveMatchedTokens(Boolean removeMatchedTokens) {
    this.removeMatchedTokens = removeMatchedTokens;
  }

  public SearchOverrideSchema removeMatchedTokens(Boolean removeMatchedTokens) {
    this.removeMatchedTokens = removeMatchedTokens;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchOverrideSchema {\n");
    
    sb.append("    rule: ").append(toIndentedString(rule)).append("\n");
    sb.append("    includes: ").append(toIndentedString(includes)).append("\n");
    sb.append("    excludes: ").append(toIndentedString(excludes)).append("\n");
    sb.append("    filterBy: ").append(toIndentedString(filterBy)).append("\n");
    sb.append("    removeMatchedTokens: ").append(toIndentedString(removeMatchedTokens)).append("\n");
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
