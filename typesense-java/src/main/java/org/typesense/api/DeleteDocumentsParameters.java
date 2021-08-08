package org.typesense.api;


import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteDocumentsParameters {

  private String filterBy = null;

  /**
   * Batch size parameter controls the number of documents that should be deleted at a time. A larger value will speed up deletions, but will impact performance of other operations running on the server.  
  **/
  private Integer batchSize = null;
 /**
   * Get filterBy
   * @return filterBy
  **/
  @JsonProperty("filter_by")
  public String getFilterBy() {
    return filterBy;
  }

  public void setFilterBy(String filterBy) {
    this.filterBy = filterBy;
  }

  public DeleteDocumentsParameters filterBy(String filterBy) {
    this.filterBy = filterBy;
    return this;
  }

 /**
   * Batch size parameter controls the number of documents that should be deleted at a time. A larger value will speed up deletions, but will impact performance of other operations running on the server.
   * @return batchSize
  **/
  @JsonProperty("batch_size")
  public Integer getBatchSize() {
    return batchSize;
  }

  public void setBatchSize(Integer batchSize) {
    this.batchSize = batchSize;
  }

  public DeleteDocumentsParameters batchSize(Integer batchSize) {
    this.batchSize = batchSize;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeleteDocumentsParameters {\n");
    
    sb.append("    filterBy: ").append(toIndentedString(filterBy)).append("\n");
    sb.append("    batchSize: ").append(toIndentedString(batchSize)).append("\n");
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
