package org.typesense.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.typesense.model.SearchHighlight;

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

public class SearchResultHit   {
  
  @Schema(description = "(Deprecated) Contains highlighted portions of the search fields")
 /**
   * (Deprecated) Contains highlighted portions of the search fields  
  **/
  private List<SearchHighlight> highlights = null;
  
  @Schema(description = "Highlighted version of the matching document")
 /**
   * Highlighted version of the matching document  
  **/
  private Map<String, Object> highlight = null;
  
  @Schema(description = "Can be any key-value pair")
 /**
   * Can be any key-value pair  
  **/
  private Map<String, Object> document = null;
  
  @Schema(description = "")
  private Long textMatch = null;
  
  @Schema(description = "Can be any key-value pair")
 /**
   * Can be any key-value pair  
  **/
  private Map<String, Integer> geoDistanceMeters = null;
  
  @Schema(description = "Distance between the query vector and matching document's vector value")
 /**
   * Distance between the query vector and matching document's vector value  
  **/
  private Float vectorDistance = null;
 /**
   * (Deprecated) Contains highlighted portions of the search fields
   * @return highlights
  **/
  @JsonProperty("highlights")
  public List<SearchHighlight> getHighlights() {
    return highlights;
  }

  public void setHighlights(List<SearchHighlight> highlights) {
    this.highlights = highlights;
  }

  public SearchResultHit highlights(List<SearchHighlight> highlights) {
    this.highlights = highlights;
    return this;
  }

  public SearchResultHit addHighlightsItem(SearchHighlight highlightsItem) {
    this.highlights.add(highlightsItem);
    return this;
  }

 /**
   * Highlighted version of the matching document
   * @return highlight
  **/
  @JsonProperty("highlight")
  public Map<String, Object> getHighlight() {
    return highlight;
  }

  public void setHighlight(Map<String, Object> highlight) {
    this.highlight = highlight;
  }

  public SearchResultHit highlight(Map<String, Object> highlight) {
    this.highlight = highlight;
    return this;
  }

  public SearchResultHit putHighlightItem(String key, Object highlightItem) {
    this.highlight.put(key, highlightItem);
    return this;
  }

 /**
   * Can be any key-value pair
   * @return document
  **/
  @JsonProperty("document")
  public Map<String, Object> getDocument() {
    return document;
  }

  public void setDocument(Map<String, Object> document) {
    this.document = document;
  }

  public SearchResultHit document(Map<String, Object> document) {
    this.document = document;
    return this;
  }

  public SearchResultHit putDocumentItem(String key, Object documentItem) {
    this.document.put(key, documentItem);
    return this;
  }

 /**
   * Get textMatch
   * @return textMatch
  **/
  @JsonProperty("text_match")
  public Long getTextMatch() {
    return textMatch;
  }

  public void setTextMatch(Long textMatch) {
    this.textMatch = textMatch;
  }

  public SearchResultHit textMatch(Long textMatch) {
    this.textMatch = textMatch;
    return this;
  }

 /**
   * Can be any key-value pair
   * @return geoDistanceMeters
  **/
  @JsonProperty("geo_distance_meters")
  public Map<String, Integer> getGeoDistanceMeters() {
    return geoDistanceMeters;
  }

  public void setGeoDistanceMeters(Map<String, Integer> geoDistanceMeters) {
    this.geoDistanceMeters = geoDistanceMeters;
  }

  public SearchResultHit geoDistanceMeters(Map<String, Integer> geoDistanceMeters) {
    this.geoDistanceMeters = geoDistanceMeters;
    return this;
  }

  public SearchResultHit putGeoDistanceMetersItem(String key, Integer geoDistanceMetersItem) {
    this.geoDistanceMeters.put(key, geoDistanceMetersItem);
    return this;
  }

 /**
   * Distance between the query vector and matching document&#x27;s vector value
   * @return vectorDistance
  **/
  @JsonProperty("vector_distance")
  public Float getVectorDistance() {
    return vectorDistance;
  }

  public void setVectorDistance(Float vectorDistance) {
    this.vectorDistance = vectorDistance;
  }

  public SearchResultHit vectorDistance(Float vectorDistance) {
    this.vectorDistance = vectorDistance;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchResultHit {\n");
    
    sb.append("    highlights: ").append(toIndentedString(highlights)).append("\n");
    sb.append("    highlight: ").append(toIndentedString(highlight)).append("\n");
    sb.append("    document: ").append(toIndentedString(document)).append("\n");
    sb.append("    textMatch: ").append(toIndentedString(textMatch)).append("\n");
    sb.append("    geoDistanceMeters: ").append(toIndentedString(geoDistanceMeters)).append("\n");
    sb.append("    vectorDistance: ").append(toIndentedString(vectorDistance)).append("\n");
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
