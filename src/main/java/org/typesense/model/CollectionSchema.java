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
  
  @Schema(example = "[{\"name\":\"num_employees\",\"type\":\"int32\",\"facet\":false},{\"name\":\"company_name\",\"type\":\"string\",\"facet\":false},{\"name\":\"country\",\"type\":\"string\",\"facet\":true}]", required = true, description = "A list of fields for querying, filtering and faceting")
 /**
   * A list of fields for querying, filtering and faceting  
  **/
  private List<Field> fields = new ArrayList<Field>();
  
  @Schema(example = "num_employees", description = "The name of an int32 / float field that determines the order in which the search results are ranked when a sort_by clause is not provided during searching. This field must indicate some kind of popularity.")
 /**
   * The name of an int32 / float field that determines the order in which the search results are ranked when a sort_by clause is not provided during searching. This field must indicate some kind of popularity.  
  **/
  private String defaultSortingField = "";
  
  @Schema(description = "List of symbols or special characters to be used for  splitting the text into individual words in addition to space and new-line characters. ")
 /**
   * List of symbols or special characters to be used for  splitting the text into individual words in addition to space and new-line characters.   
  **/
  private List<String> tokenSeparators = null;
  
  @Schema(example = "true", description = "Enables experimental support at a collection level for nested object or object array fields. This field is only available if the Typesense server is version `0.24.0.rcn34` or later.")
 /**
   * Enables experimental support at a collection level for nested object or object array fields. This field is only available if the Typesense server is version `0.24.0.rcn34` or later.  
  **/
  private Boolean enableNestedFields = false;
  
  @Schema(description = "List of symbols or special characters to be indexed. ")
 /**
   * List of symbols or special characters to be indexed.   
  **/
  private List<String> symbolsToIndex = null;
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

 /**
   * List of symbols or special characters to be used for  splitting the text into individual words in addition to space and new-line characters. 
   * @return tokenSeparators
  **/
  @JsonProperty("token_separators")
  public List<String> getTokenSeparators() {
    return tokenSeparators;
  }

  public void setTokenSeparators(List<String> tokenSeparators) {
    this.tokenSeparators = tokenSeparators;
  }

  public CollectionSchema tokenSeparators(List<String> tokenSeparators) {
    this.tokenSeparators = tokenSeparators;
    return this;
  }

  public CollectionSchema addTokenSeparatorsItem(String tokenSeparatorsItem) {
    this.tokenSeparators.add(tokenSeparatorsItem);
    return this;
  }

 /**
   * Enables experimental support at a collection level for nested object or object array fields. This field is only available if the Typesense server is version &#x60;0.24.0.rcn34&#x60; or later.
   * @return enableNestedFields
  **/
  @JsonProperty("enable_nested_fields")
  public Boolean isEnableNestedFields() {
    return enableNestedFields;
  }

  public void setEnableNestedFields(Boolean enableNestedFields) {
    this.enableNestedFields = enableNestedFields;
  }

  public CollectionSchema enableNestedFields(Boolean enableNestedFields) {
    this.enableNestedFields = enableNestedFields;
    return this;
  }

 /**
   * List of symbols or special characters to be indexed. 
   * @return symbolsToIndex
  **/
  @JsonProperty("symbols_to_index")
  public List<String> getSymbolsToIndex() {
    return symbolsToIndex;
  }

  public void setSymbolsToIndex(List<String> symbolsToIndex) {
    this.symbolsToIndex = symbolsToIndex;
  }

  public CollectionSchema symbolsToIndex(List<String> symbolsToIndex) {
    this.symbolsToIndex = symbolsToIndex;
    return this;
  }

  public CollectionSchema addSymbolsToIndexItem(String symbolsToIndexItem) {
    this.symbolsToIndex.add(symbolsToIndexItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollectionSchema {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
    sb.append("    defaultSortingField: ").append(toIndentedString(defaultSortingField)).append("\n");
    sb.append("    tokenSeparators: ").append(toIndentedString(tokenSeparators)).append("\n");
    sb.append("    enableNestedFields: ").append(toIndentedString(enableNestedFields)).append("\n");
    sb.append("    symbolsToIndex: ").append(toIndentedString(symbolsToIndex)).append("\n");
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
