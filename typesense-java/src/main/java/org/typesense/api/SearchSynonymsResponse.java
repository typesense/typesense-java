package org.typesense.api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchSynonymsResponse   {

  private List<SearchSynonym> synonyms = new ArrayList<SearchSynonym>();
 /**
   * Get synonyms
   * @return synonyms
  **/
  @JsonProperty("synonyms")
  public List<SearchSynonym> getSynonyms() {
    return synonyms;
  }

  public void setSynonyms(List<SearchSynonym> synonyms) {
    this.synonyms = synonyms;
  }

  public SearchSynonymsResponse synonyms(List<SearchSynonym> synonyms) {
    this.synonyms = synonyms;
    return this;
  }

  public SearchSynonymsResponse addSynonymsItem(SearchSynonym synonymsItem) {
    this.synonyms.add(synonymsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchSynonymsResponse {\n");
    
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
