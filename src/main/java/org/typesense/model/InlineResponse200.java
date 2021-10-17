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

public class InlineResponse200   {
  
  @Schema(required = true, description = "")
  private Integer numDeleted = null;
 /**
   * Get numDeleted
   * @return numDeleted
  **/
  @JsonProperty("num_deleted")
  public Integer getNumDeleted() {
    return numDeleted;
  }

  public void setNumDeleted(Integer numDeleted) {
    this.numDeleted = numDeleted;
  }

  public InlineResponse200 numDeleted(Integer numDeleted) {
    this.numDeleted = numDeleted;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200 {\n");
    
    sb.append("    numDeleted: ").append(toIndentedString(numDeleted)).append("\n");
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
