package org.typesense.model;

import java.util.ArrayList;
import java.util.List;
import org.typesense.model.SearchResult;

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

public class MultiSearchResult   {
  
  @Schema(required = true, description = "")
  private List<SearchResult> results = new ArrayList<SearchResult>();
 /**
   * Get results
   * @return results
  **/
  @JsonProperty("results")
  public List<SearchResult> getResults() {
    return results;
  }

  public void setResults(List<SearchResult> results) {
    this.results = results;
  }

  public MultiSearchResult results(List<SearchResult> results) {
    this.results = results;
    return this;
  }

  public MultiSearchResult addResultsItem(SearchResult resultsItem) {
    this.results.add(resultsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MultiSearchResult {\n");
    
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
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
