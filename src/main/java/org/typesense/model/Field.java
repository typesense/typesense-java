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
  public enum TypeEnum {
    STRING("string"),
    INT32("int32"),
    INT64("int64"),
    FLOAT("float"),
    BOOL("bool"),
    STRING_("string[]"),
    INT32_("int32[]"),
    INT64_("int64[]"),
    FLOAT_("float[]"),
    BOOL_("bool[]"),
    AUTO("auto");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }  
  @Schema(example = "string", required = true, description = "")
  private TypeEnum type = null;
  
  @Schema(example = "true", required = true, description = "")
  private Boolean optional = false;
  
  @Schema(example = "false", required = true, description = "")
  private Boolean facet = false;
  
  @Schema(example = "false", description = "")
  private Boolean index = null;
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
    if (type == null) {
      return null;
    }
    return type.getValue();
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public Field type(TypeEnum type) {
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


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Field {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    optional: ").append(toIndentedString(optional)).append("\n");
    sb.append("    facet: ").append(toIndentedString(facet)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
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
