package org.typesense.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExportDocumentsParameters {
  
 /**
   * Filter conditions for refining your search results. Separate multiple conditions with &&.  
  **/
  private String filterBy = null;
  
  /**
   * List of fields from the document to include in the search result  
  **/
  private List<String> includeFields = new ArrayList<>();
  
  /**
   * List of fields from the document to exclude in the search result  
  **/
  private List<String> excludeFields = new ArrayList<>();
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
  public List<String> getIncludeFields() {
    return includeFields;
  }

  public void setIncludeFields(List<String> includeFields) {
    this.includeFields = includeFields;
  }

  public ExportDocumentsParameters includeFields(List<String> includeFields) {
    this.includeFields = includeFields;
    return this;
  }

  public ExportDocumentsParameters addIncludeFieldsItem(String includeFieldsItem) {
    this.includeFields.add(includeFieldsItem);
    return this;
  }

 /**
   * List of fields from the document to exclude in the search result
   * @return excludeFields
  **/
  @JsonProperty("exclude_fields")
  public List<String> getExcludeFields() {
    return excludeFields;
  }

  public void setExcludeFields(List<String> excludeFields) {
    this.excludeFields = excludeFields;
  }

  public ExportDocumentsParameters excludeFields(List<String> excludeFields) {
    this.excludeFields = excludeFields;
    return this;
  }

  public ExportDocumentsParameters addExcludeFieldsItem(String excludeFieldsItem) {
    this.excludeFields.add(excludeFieldsItem);
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
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
