package org.typesense.model;

import java.util.ArrayList;
import java.util.List;
import org.typesense.model.FacetCountsCounts;
import org.typesense.model.FacetCountsStats;

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

public class FacetCounts   {
  
  @Schema(description = "")
  private List<FacetCountsCounts> counts = null;
  
  @Schema(description = "")
  private String fieldName = null;
  
  @Schema(description = "")
  private FacetCountsStats stats = null;
 /**
   * Get counts
   * @return counts
  **/
  @JsonProperty("counts")
  public List<FacetCountsCounts> getCounts() {
    return counts;
  }

  public void setCounts(List<FacetCountsCounts> counts) {
    this.counts = counts;
  }

  public FacetCounts counts(List<FacetCountsCounts> counts) {
    this.counts = counts;
    return this;
  }

  public FacetCounts addCountsItem(FacetCountsCounts countsItem) {
    this.counts.add(countsItem);
    return this;
  }

 /**
   * Get fieldName
   * @return fieldName
  **/
  @JsonProperty("field_name")
  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public FacetCounts fieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

 /**
   * Get stats
   * @return stats
  **/
  @JsonProperty("stats")
  public FacetCountsStats getStats() {
    return stats;
  }

  public void setStats(FacetCountsStats stats) {
    this.stats = stats;
  }

  public FacetCounts stats(FacetCountsStats stats) {
    this.stats = stats;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FacetCounts {\n");
    
    sb.append("    counts: ").append(toIndentedString(counts)).append("\n");
    sb.append("    fieldName: ").append(toIndentedString(fieldName)).append("\n");
    sb.append("    stats: ").append(toIndentedString(stats)).append("\n");
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
