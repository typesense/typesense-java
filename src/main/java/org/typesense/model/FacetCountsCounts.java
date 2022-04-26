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

public class FacetCountsCounts   {
  
  @Schema(description = "")
  private Integer count = null;
  
  @Schema(description = "")
  private String highlighted = null;
  
  @Schema(description = "")
  private String value = null;
 /**
   * Get count
   * @return count
  **/
  @JsonProperty("count")
  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public FacetCountsCounts count(Integer count) {
    this.count = count;
    return this;
  }

 /**
   * Get highlighted
   * @return highlighted
  **/
  @JsonProperty("highlighted")
  public String getHighlighted() {
    return highlighted;
  }

  public void setHighlighted(String highlighted) {
    this.highlighted = highlighted;
  }

  public FacetCountsCounts highlighted(String highlighted) {
    this.highlighted = highlighted;
    return this;
  }

 /**
   * Get value
   * @return value
  **/
  @JsonProperty("value")
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public FacetCountsCounts value(String value) {
    this.value = value;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FacetCountsCounts {\n");
    
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    highlighted: ").append(toIndentedString(highlighted)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
