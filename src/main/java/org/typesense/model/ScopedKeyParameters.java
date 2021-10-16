package org.typesense.model;

import java.math.BigDecimal;

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

public class ScopedKeyParameters   {
  
  @Schema(description = "")
  private String filterBy = null;
  
  @Schema(description = "")
  private BigDecimal expiresAt = null;
 /**
   * Get filterBy
   * @return filterBy
  **/
  @JsonProperty("filter_by")
  public String getFilterBy() {
    return filterBy;
  }

  public void setFilterBy(String filterBy) {
    this.filterBy = filterBy;
  }

  public ScopedKeyParameters filterBy(String filterBy) {
    this.filterBy = filterBy;
    return this;
  }

 /**
   * Get expiresAt
   * @return expiresAt
  **/
  @JsonProperty("expires_at")
  public BigDecimal getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(BigDecimal expiresAt) {
    this.expiresAt = expiresAt;
  }

  public ScopedKeyParameters expiresAt(BigDecimal expiresAt) {
    this.expiresAt = expiresAt;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScopedKeyParameters {\n");
    
    sb.append("    filterBy: ").append(toIndentedString(filterBy)).append("\n");
    sb.append("    expiresAt: ").append(toIndentedString(expiresAt)).append("\n");
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
