package org.typesense.model;


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

public class SearchOverrideRule   {
  
  @Schema(required = true, description = "Indicates what search queries should be overridden")
 /**
   * Indicates what search queries should be overridden  
  **/
  private String query = null;
  public enum MatchEnum {
    EXACT("exact"),
    CONTAINS("contains");

    private String value;

    MatchEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static MatchEnum fromValue(String text) {
      for (MatchEnum b : MatchEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }  
  @Schema(required = true, description = "Indicates whether the match on the query term should be `exact` or `contains`. If we want to match all queries that contained the word `apple`, we will use the `contains` match instead. ")
 /**
   * Indicates whether the match on the query term should be `exact` or `contains`. If we want to match all queries that contained the word `apple`, we will use the `contains` match instead.   
  **/
  private MatchEnum match = null;
 /**
   * Indicates what search queries should be overridden
   * @return query
  **/
  @JsonProperty("query")
  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public SearchOverrideRule query(String query) {
    this.query = query;
    return this;
  }

 /**
   * Indicates whether the match on the query term should be &#x60;exact&#x60; or &#x60;contains&#x60;. If we want to match all queries that contained the word &#x60;apple&#x60;, we will use the &#x60;contains&#x60; match instead. 
   * @return match
  **/
  @JsonProperty("match")
  public String getMatch() {
    if (match == null) {
      return null;
    }
    return match.getValue();
  }

  public void setMatch(MatchEnum match) {
    this.match = match;
  }

  public SearchOverrideRule match(MatchEnum match) {
    this.match = match;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchOverrideRule {\n");
    
    sb.append("    query: ").append(toIndentedString(query)).append("\n");
    sb.append("    match: ").append(toIndentedString(match)).append("\n");
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
