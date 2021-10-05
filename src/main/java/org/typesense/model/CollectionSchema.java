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

public class CollectionSchema   {
  
  @Schema(example = "companies", required = true, description = "Name of the collection")
 /**
   * Name of the collection  
  **/
  private String name = null;
  
  @Schema(example = "[{\"name\":\"company_name\",\"type\":\"string\",\"facet\":false},{\"name\":\"num_employees\",\"type\":\"int32\",\"facet\":false},{\"name\":\"country\",\"type\":\"string\",\"facet\":true}]", required = true, description = "A list of fields for querying, filtering and faceting")
 /**
   * A list of fields for querying, filtering and faceting  
  **/
  private List<Field> fields = new ArrayList<Field>();
  
  @Schema(example = "num_employees", description = "The name of an int32 / float field that determines the order in which the search results are ranked when a sort_by clause is not provided during searching. This field must indicate some kind of popularity.")
 /**
   * The name of an int32 / float field that determines the order in which the search results are ranked when a sort_by clause is not provided during searching. This field must indicate some kind of popularity.  
  **/
  private String defaultSortingField = "";
 /**
   * Name of the collection
   * @return name
  **/
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CollectionSchema name(String name) {
    this.name = name;
    return this;
  }

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

  public CollectionSchema fields(List<Field> fields) {
    this.fields = fields;
    return this;
  }

  public CollectionSchema addFieldsItem(Field fieldsItem) {
    this.fields.add(fieldsItem);
    return this;
  }

 /**
   * The name of an int32 / float field that determines the order in which the search results are ranked when a sort_by clause is not provided during searching. This field must indicate some kind of popularity.
   * @return defaultSortingField
  **/
  @JsonProperty("default_sorting_field")
  public String getDefaultSortingField() {
    return defaultSortingField;
  }

  public void setDefaultSortingField(String defaultSortingField) {
    this.defaultSortingField = defaultSortingField;
  }

  public CollectionSchema defaultSortingField(String defaultSortingField) {
    this.defaultSortingField = defaultSortingField;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollectionSchema {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
    sb.append("    defaultSortingField: ").append(toIndentedString(defaultSortingField)).append("\n");
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
