package org.typesense.model;

import java.util.ArrayList;
import java.util.List;

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

public class ApiKeySchema   {
  
  @Schema(required = true, description = "")
  private String description = null;
  
  @Schema(required = true, description = "")
  private List<String> actions = new ArrayList<String>();
  
  @Schema(required = true, description = "")
  private List<String> collections = new ArrayList<String>();
  
  @Schema(description = "")
  private Long expiresAt = null;
 /**
   * Get description
   * @return description
  **/
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ApiKeySchema description(String description) {
    this.description = description;
    return this;
  }

 /**
   * Get actions
   * @return actions
  **/
  @JsonProperty("actions")
  public List<String> getActions() {
    return actions;
  }

  public void setActions(List<String> actions) {
    this.actions = actions;
  }

  public ApiKeySchema actions(List<String> actions) {
    this.actions = actions;
    return this;
  }

  public ApiKeySchema addActionsItem(String actionsItem) {
    this.actions.add(actionsItem);
    return this;
  }

 /**
   * Get collections
   * @return collections
  **/
  @JsonProperty("collections")
  public List<String> getCollections() {
    return collections;
  }

  public void setCollections(List<String> collections) {
    this.collections = collections;
  }

  public ApiKeySchema collections(List<String> collections) {
    this.collections = collections;
    return this;
  }

  public ApiKeySchema addCollectionsItem(String collectionsItem) {
    this.collections.add(collectionsItem);
    return this;
  }

 /**
   * Get expiresAt
   * @return expiresAt
  **/
  @JsonProperty("expires_at")
  public Long getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(Long expiresAt) {
    this.expiresAt = expiresAt;
  }

  public ApiKeySchema expiresAt(Long expiresAt) {
    this.expiresAt = expiresAt;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiKeySchema {\n");
    
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    actions: ").append(toIndentedString(actions)).append("\n");
    sb.append("    collections: ").append(toIndentedString(collections)).append("\n");
    sb.append("    expiresAt: ").append(toIndentedString(expiresAt)).append("\n");
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
