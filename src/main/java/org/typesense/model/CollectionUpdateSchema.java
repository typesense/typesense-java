package org.typesense.model;

import java.util.ArrayList;
import java.util.List;
import org.typesense.model.Field;

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

public class CollectionUpdateSchema   {
  
  @Schema(example = "[{\"name\":\"company_name\",\"type\":\"string\",\"facet\":false},{\"name\":\"num_employees\",\"type\":\"int32\",\"facet\":false},{\"name\":\"country\",\"type\":\"string\",\"facet\":true}]", required = true, description = "A list of fields for querying, filtering and faceting")
 /**
   * A list of fields for querying, filtering and faceting  
  **/
  private List<Field> fields = new ArrayList<Field>();
 /**
   * A list of fields for querying, filtering and faceting
   * @return fields
  **/
  @JsonProperty("fields")
  public List<Field> getFields() {
    return fields;
  }

  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  public CollectionUpdateSchema fields(List<Field> fields) {
    this.fields = fields;
    return this;
  }

  public CollectionUpdateSchema addFieldsItem(Field fieldsItem) {
    this.fields.add(fieldsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollectionUpdateSchema {\n");
    
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
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
