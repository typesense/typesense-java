package org.typesense.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchSynonymSchema   {
  
 /**
   * For 1-way synonyms, indicates the root word that words in the `synonyms` parameter map to.  
  **/
  private String root = null;
  
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
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
