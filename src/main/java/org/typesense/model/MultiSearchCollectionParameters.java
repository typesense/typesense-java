package org.typesense.model;

import org.typesense.model.MultiSearchParameters;

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

public class MultiSearchCollectionParameters extends MultiSearchParameters  {
  
  @Schema(required = true, description = "The collection to search in. ")
 /**
   * The collection to search in.   
  **/
  private String collection = null;
 /**
   * The collection to search in. 
   * @return collection
  **/
  @JsonProperty("collection")
  public String getCollection() {
    return collection;
  }

  public void setCollection(String collection) {
    this.collection = collection;
  }

  public MultiSearchCollectionParameters collection(String collection) {
    this.collection = collection;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MultiSearchCollectionParameters {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    collection: ").append(toIndentedString(collection)).append("\n");
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
