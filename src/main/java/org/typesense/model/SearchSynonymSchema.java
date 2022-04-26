package org.typesense.model;

import java.util.ArrayList;
import java.util.List;

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

public class SearchSynonymSchema   {
  
  @Schema(description = "For 1-way synonyms, indicates the root word that words in the `synonyms` parameter map to.")
 /**
   * For 1-way synonyms, indicates the root word that words in the `synonyms` parameter map to.  
  **/
  private String root = null;
  
  @Schema(required = true, description = "Array of words that should be considered as synonyms.")
 /**
   * Array of words that should be considered as synonyms.  
  **/
  private List<String> synonyms = new ArrayList<String>();
 /**
   * For 1-way synonyms, indicates the root word that words in the &#x60;synonyms&#x60; parameter map to.
   * @return root
  **/
  @JsonProperty("root")
  public String getRoot() {
    return root;
  }

  public void setRoot(String root) {
    this.root = root;
  }

  public SearchSynonymSchema root(String root) {
    this.root = root;
    return this;
  }

 /**
   * Array of words that should be considered as synonyms.
   * @return synonyms
  **/
  @JsonProperty("synonyms")
  public List<String> getSynonyms() {
    return synonyms;
  }

  public void setSynonyms(List<String> synonyms) {
    this.synonyms = synonyms;
  }

  public SearchSynonymSchema synonyms(List<String> synonyms) {
    this.synonyms = synonyms;
    return this;
  }

  public SearchSynonymSchema addSynonymsItem(String synonymsItem) {
    this.synonyms.add(synonymsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchSynonymSchema {\n");
    
    sb.append("    root: ").append(toIndentedString(root)).append("\n");
    sb.append("    synonyms: ").append(toIndentedString(synonyms)).append("\n");
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
