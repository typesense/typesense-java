package org.typesense.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchOverride extends SearchOverrideSchema  {

  private String id = null;
 /**
   * Get id
   * @return id
  **/
  @JsonProperty("id")
  public String getId() {
    return id;
  }



  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchOverride {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
