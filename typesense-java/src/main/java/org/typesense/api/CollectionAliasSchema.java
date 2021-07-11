package org.typesense.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CollectionAliasSchema   {
  
 /**
   * Name of the collection you wish to map the alias to  
  **/
  private String collectionName = null;
 /**
   * Name of the collection you wish to map the alias to
   * @return collectionName
  **/
  @JsonProperty("collection_name")
  public String getCollectionName() {
    return collectionName;
  }

  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }

  public CollectionAliasSchema collectionName(String collectionName) {
    this.collectionName = collectionName;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollectionAliasSchema {\n");
    
    sb.append("    collectionName: ").append(toIndentedString(collectionName)).append("\n");
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
