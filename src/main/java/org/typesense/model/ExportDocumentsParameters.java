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

public class ExportDocumentsParameters   {
  
  @Schema(description = "Filter conditions for refining your search results. Separate multiple conditions with &&.")
 /**
   * Filter conditions for refining your search results. Separate multiple conditions with &&.  
  **/
  private String filterBy = null;
  
  @Schema(required = true, description = "List of fields from the document to include in the search result")
 /**
   * List of fields from the document to include in the search result  
  **/
  private String includeFields = null;
  
  @Schema(required = true, description = "List of fields from the document to exclude in the search result")
 /**
   * List of fields from the document to exclude in the search result  
  **/
  private String excludeFields = null;
 /**
   * Filter conditions for refining your search results. Separate multiple conditions with &amp;&amp;.
   * @return filterBy
  **/
  @JsonProperty("filter_by")
  public String getFilterBy() {
    return filterBy;
  }

  public void setFilterBy(String filterBy) {
    this.filterBy = filterBy;
  }

  public ExportDocumentsParameters filterBy(String filterBy) {
    this.filterBy = filterBy;
    return this;
  }

 /**
   * List of fields from the document to include in the search result
   * @return includeFields
  **/
  @JsonProperty("include_fields")
  public String getIncludeFields() {
    return includeFields;
  }

  public void setIncludeFields(String includeFields) {
    this.includeFields = includeFields;
  }

  public ExportDocumentsParameters includeFields(String includeFields) {
    this.includeFields = includeFields;
    return this;
  }

 /**
   * List of fields from the document to exclude in the search result
   * @return excludeFields
  **/
  @JsonProperty("exclude_fields")
  public String getExcludeFields() {
    return excludeFields;
  }

  public void setExcludeFields(String excludeFields) {
    this.excludeFields = excludeFields;
  }

  public ExportDocumentsParameters excludeFields(String excludeFields) {
    this.excludeFields = excludeFields;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExportDocumentsParameters {\n");
    
    sb.append("    filterBy: ").append(toIndentedString(filterBy)).append("\n");
    sb.append("    includeFields: ").append(toIndentedString(includeFields)).append("\n");
    sb.append("    excludeFields: ").append(toIndentedString(excludeFields)).append("\n");
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
