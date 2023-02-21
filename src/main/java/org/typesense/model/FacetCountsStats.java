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

public class FacetCountsStats   {
  
  @Schema(description = "")
  private Double max = null;
  
  @Schema(description = "")
  private Double min = null;
  
  @Schema(description = "")
  private Double sum = null;
  
  @Schema(description = "")
  private Integer totalValues = null;
  
  @Schema(description = "")
  private Double avg = null;
 /**
   * Get max
   * @return max
  **/
  @JsonProperty("max")
  public Double getMax() {
    return max;
  }

  public void setMax(Double max) {
    this.max = max;
  }

  public FacetCountsStats max(Double max) {
    this.max = max;
    return this;
  }

 /**
   * Get min
   * @return min
  **/
  @JsonProperty("min")
  public Double getMin() {
    return min;
  }

  public void setMin(Double min) {
    this.min = min;
  }

  public FacetCountsStats min(Double min) {
    this.min = min;
    return this;
  }

 /**
   * Get sum
   * @return sum
  **/
  @JsonProperty("sum")
  public Double getSum() {
    return sum;
  }

  public void setSum(Double sum) {
    this.sum = sum;
  }

  public FacetCountsStats sum(Double sum) {
    this.sum = sum;
    return this;
  }

 /**
   * Get totalValues
   * @return totalValues
  **/
  @JsonProperty("total_values")
  public Integer getTotalValues() {
    return totalValues;
  }

  public void setTotalValues(Integer totalValues) {
    this.totalValues = totalValues;
  }

  public FacetCountsStats totalValues(Integer totalValues) {
    this.totalValues = totalValues;
    return this;
  }

 /**
   * Get avg
   * @return avg
  **/
  @JsonProperty("avg")
  public Double getAvg() {
    return avg;
  }

  public void setAvg(Double avg) {
    this.avg = avg;
  }

  public FacetCountsStats avg(Double avg) {
    this.avg = avg;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FacetCountsStats {\n");
    
    sb.append("    max: ").append(toIndentedString(max)).append("\n");
    sb.append("    min: ").append(toIndentedString(min)).append("\n");
    sb.append("    sum: ").append(toIndentedString(sum)).append("\n");
    sb.append("    totalValues: ").append(toIndentedString(totalValues)).append("\n");
    sb.append("    avg: ").append(toIndentedString(avg)).append("\n");
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
