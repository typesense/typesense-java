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

public class Field   {
  
  @Schema(example = "company_name", required = true, description = "")
  private String name = null;
  
  @Schema(example = "string", required = true, description = "")
  private String type = null;
  
  @Schema(example = "true", description = "")
  private Boolean optional = null;
  
  @Schema(example = "false", description = "")
  private Boolean facet = null;
  
  @Schema(example = "true", description = "")
  private Boolean index = true;
  
  @Schema(example = "el", description = "")
  private String locale = null;
  
  @Schema(example = "true", description = "")
  private Boolean sort = null;
  
  @Schema(example = "true", description = "")
  private Boolean infix = false;
  
  @Schema(example = "256", description = "")
  private Integer numDim = null;
  
  @Schema(example = "true", description = "")
  private Boolean drop = null;
 /**
   * Get name
   * @return name
  **/
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Field name(String name) {
    this.name = name;
    return this;
  }

 /**
   * Get type
   * @return type
  **/
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Field type(String type) {
    this.type = type;
    return this;
  }

 /**
   * Get optional
   * @return optional
  **/
  @JsonProperty("optional")
  public Boolean isOptional() {
    return optional;
  }

  public void setOptional(Boolean optional) {
    this.optional = optional;
  }

  public Field optional(Boolean optional) {
    this.optional = optional;
    return this;
  }

 /**
   * Get facet
   * @return facet
  **/
  @JsonProperty("facet")
  public Boolean isFacet() {
    return facet;
  }

  public void setFacet(Boolean facet) {
    this.facet = facet;
  }

  public Field facet(Boolean facet) {
    this.facet = facet;
    return this;
  }

 /**
   * Get index
   * @return index
  **/
  @JsonProperty("index")
  public Boolean isIndex() {
    return index;
  }

  public void setIndex(Boolean index) {
    this.index = index;
  }

  public Field index(Boolean index) {
    this.index = index;
    return this;
  }

 /**
   * Get locale
   * @return locale
  **/
  @JsonProperty("locale")
  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public Field locale(String locale) {
    this.locale = locale;
    return this;
  }

 /**
   * Get sort
   * @return sort
  **/
  @JsonProperty("sort")
  public Boolean isSort() {
    return sort;
  }

  public void setSort(Boolean sort) {
    this.sort = sort;
  }

  public Field sort(Boolean sort) {
    this.sort = sort;
    return this;
  }

 /**
   * Get infix
   * @return infix
  **/
  @JsonProperty("infix")
  public Boolean isInfix() {
    return infix;
  }

  public void setInfix(Boolean infix) {
    this.infix = infix;
  }

  public Field infix(Boolean infix) {
    this.infix = infix;
    return this;
  }

 /**
   * Get numDim
   * @return numDim
  **/
  @JsonProperty("num_dim")
  public Integer getNumDim() {
    return numDim;
  }

  public void setNumDim(Integer numDim) {
    this.numDim = numDim;
  }

  public Field numDim(Integer numDim) {
    this.numDim = numDim;
    return this;
  }

 /**
   * Get drop
   * @return drop
  **/
  @JsonProperty("drop")
  public Boolean isDrop() {
    return drop;
  }

  public void setDrop(Boolean drop) {
    this.drop = drop;
  }

  public Field drop(Boolean drop) {
    this.drop = drop;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Field {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    optional: ").append(toIndentedString(optional)).append("\n");
    sb.append("    facet: ").append(toIndentedString(facet)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    infix: ").append(toIndentedString(infix)).append("\n");
    sb.append("    numDim: ").append(toIndentedString(numDim)).append("\n");
    sb.append("    drop: ").append(toIndentedString(drop)).append("\n");
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
