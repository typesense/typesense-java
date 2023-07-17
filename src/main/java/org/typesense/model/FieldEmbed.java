package org.typesense.model;

import java.util.List;
import org.typesense.model.FieldEmbedModelConfig;

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

public class FieldEmbed   {
  
  @Schema(required = true, description = "")
  private List from = null;
  
  @Schema(required = true, description = "")
  private FieldEmbedModelConfig modelConfig = null;
 /**
   * Get from
   * @return from
  **/
  @JsonProperty("from")
  public List getFrom() {
    return from;
  }

  public void setFrom(List from) {
    this.from = from;
  }

  public FieldEmbed from(List from) {
    this.from = from;
    return this;
  }

 /**
   * Get modelConfig
   * @return modelConfig
  **/
  @JsonProperty("model_config")
  public FieldEmbedModelConfig getModelConfig() {
    return modelConfig;
  }

  public void setModelConfig(FieldEmbedModelConfig modelConfig) {
    this.modelConfig = modelConfig;
  }

  public FieldEmbed modelConfig(FieldEmbedModelConfig modelConfig) {
    this.modelConfig = modelConfig;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FieldEmbed {\n");
    
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    modelConfig: ").append(toIndentedString(modelConfig)).append("\n");
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
