package org.typesense.api;

import java.util.ArrayList;
import java.util.List;

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
