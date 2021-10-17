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

public class ImportDocumentsParameters   {
  
  @Schema(description = "")
  private String action = null;
  
  @Schema(description = "")
  private Integer batchSize = null;
 /**
   * Get action
   * @return action
  **/
  @JsonProperty("action")
  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public ImportDocumentsParameters action(String action) {
    this.action = action;
    return this;
  }

 /**
   * Get batchSize
   * @return batchSize
  **/
  @JsonProperty("batch_size")
  public Integer getBatchSize() {
    return batchSize;
  }

  public void setBatchSize(Integer batchSize) {
    this.batchSize = batchSize;
  }

  public ImportDocumentsParameters batchSize(Integer batchSize) {
    this.batchSize = batchSize;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImportDocumentsParameters {\n");
    
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    batchSize: ").append(toIndentedString(batchSize)).append("\n");
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
