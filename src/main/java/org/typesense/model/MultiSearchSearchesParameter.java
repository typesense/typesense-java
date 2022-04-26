package org.typesense.model;

import java.util.ArrayList;
import java.util.List;
import org.typesense.model.MultiSearchCollectionParameters;

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

public class MultiSearchSearchesParameter   {
  
  @Schema(required = true, description = "")
  private List<MultiSearchCollectionParameters> searches = new ArrayList<MultiSearchCollectionParameters>();
 /**
   * Get searches
   * @return searches
  **/
  @JsonProperty("searches")
  public List<MultiSearchCollectionParameters> getSearches() {
    return searches;
  }

  public void setSearches(List<MultiSearchCollectionParameters> searches) {
    this.searches = searches;
  }

  public MultiSearchSearchesParameter searches(List<MultiSearchCollectionParameters> searches) {
    this.searches = searches;
    return this;
  }

  public MultiSearchSearchesParameter addSearchesItem(MultiSearchCollectionParameters searchesItem) {
    this.searches.add(searchesItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MultiSearchSearchesParameter {\n");
    
    sb.append("    searches: ").append(toIndentedString(searches)).append("\n");
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
