package org.typesense.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchOverrideInclude   {

 /**
   * document id that should be included  
  **/
  private String id = null;
  
  /**
   * position number where document should be included in the search results  
  **/
  private Integer position = null;
 /**
   * document id that should be included
   * @return id
  **/
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SearchOverrideInclude id(String id) {
    this.id = id;
    return this;
  }

 /**
   * position number where document should be included in the search results
   * @return position
  **/
  @JsonProperty("position")
  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public SearchOverrideInclude position(Integer position) {
    this.position = position;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchOverrideInclude {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
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
