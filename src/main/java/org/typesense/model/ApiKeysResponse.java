package org.typesense.model;

import java.util.ArrayList;
import java.util.List;
import org.typesense.model.ApiKey;

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

public class ApiKeysResponse   {
  
  @Schema(required = true, description = "")
  private List<ApiKey> keys = new ArrayList<ApiKey>();
 /**
   * Get keys
   * @return keys
  **/
  @JsonProperty("keys")
  public List<ApiKey> getKeys() {
    return keys;
  }

  public void setKeys(List<ApiKey> keys) {
    this.keys = keys;
  }

  public ApiKeysResponse keys(List<ApiKey> keys) {
    this.keys = keys;
    return this;
  }

  public ApiKeysResponse addKeysItem(ApiKey keysItem) {
    this.keys.add(keysItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiKeysResponse {\n");
    
    sb.append("    keys: ").append(toIndentedString(keys)).append("\n");
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
