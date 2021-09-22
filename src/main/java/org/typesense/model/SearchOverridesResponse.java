package org.typesense.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchOverridesResponse   {

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
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
