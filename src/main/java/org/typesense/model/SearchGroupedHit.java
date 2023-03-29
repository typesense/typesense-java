package org.typesense.model;

import java.util.ArrayList;
import java.util.List;
import org.typesense.model.SearchResultHit;

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

public class SearchGroupedHit   {
  
  @Schema(required = true, description = "")
  private List<Object> groupKey = new ArrayList<Object>();
  
  @Schema(required = true, description = "The documents that matched the search query")
 /**
   * The documents that matched the search query  
  **/
  private List<SearchResultHit> hits = new ArrayList<SearchResultHit>();
 /**
   * Get groupKey
   * @return groupKey
  **/
  @JsonProperty("group_key")
  public List<Object> getGroupKey() {
    return groupKey;
  }

  public void setGroupKey(List<Object> groupKey) {
    this.groupKey = groupKey;
  }

  public SearchGroupedHit groupKey(List<Object> groupKey) {
    this.groupKey = groupKey;
    return this;
  }

  public SearchGroupedHit addGroupKeyItem(Object groupKeyItem) {
    this.groupKey.add(groupKeyItem);
    return this;
  }

 /**
   * The documents that matched the search query
   * @return hits
  **/
  @JsonProperty("hits")
  public List<SearchResultHit> getHits() {
    return hits;
  }

  public void setHits(List<SearchResultHit> hits) {
    this.hits = hits;
  }

  public SearchGroupedHit hits(List<SearchResultHit> hits) {
    this.hits = hits;
    return this;
  }

  public SearchGroupedHit addHitsItem(SearchResultHit hitsItem) {
    this.hits.add(hitsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchGroupedHit {\n");
    
    sb.append("    groupKey: ").append(toIndentedString(groupKey)).append("\n");
    sb.append("    hits: ").append(toIndentedString(hits)).append("\n");
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
