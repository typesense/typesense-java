package org.typesense.model;

import java.util.ArrayList;
import java.util.List;
import org.typesense.model.SearchOverride;

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

public class SearchOverridesResponse   {
  
  @Schema(required = true, description = "")
  private List<SearchOverride> overrides = new ArrayList<SearchOverride>();
 /**
   * Get overrides
   * @return overrides
  **/
  @JsonProperty("overrides")
  public List<SearchOverride> getOverrides() {
    return overrides;
  }

  public void setOverrides(List<SearchOverride> overrides) {
    this.overrides = overrides;
  }

  public SearchOverridesResponse overrides(List<SearchOverride> overrides) {
    this.overrides = overrides;
    return this;
  }

  public SearchOverridesResponse addOverridesItem(SearchOverride overridesItem) {
    this.overrides.add(overridesItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchOverridesResponse {\n");
    
    sb.append("    overrides: ").append(toIndentedString(overrides)).append("\n");
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
