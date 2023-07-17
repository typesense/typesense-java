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

public class FieldEmbedModelConfig   {
  
  @Schema(required = true, description = "")
  private String modelName = null;
  
  @Schema(description = "")
  private String apiKey = null;
  
  @Schema(description = "")
  private String accessToken = null;
  
  @Schema(description = "")
  private String clientId = null;
  
  @Schema(description = "")
  private String clientSecret = null;
  
  @Schema(description = "")
  private String projectId = null;
 /**
   * Get modelName
   * @return modelName
  **/
  @JsonProperty("model_name")
  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public FieldEmbedModelConfig modelName(String modelName) {
    this.modelName = modelName;
    return this;
  }

 /**
   * Get apiKey
   * @return apiKey
  **/
  @JsonProperty("api_key")
  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public FieldEmbedModelConfig apiKey(String apiKey) {
    this.apiKey = apiKey;
    return this;
  }

 /**
   * Get accessToken
   * @return accessToken
  **/
  @JsonProperty("access_token")
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public FieldEmbedModelConfig accessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

 /**
   * Get clientId
   * @return clientId
  **/
  @JsonProperty("client_id")
  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public FieldEmbedModelConfig clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

 /**
   * Get clientSecret
   * @return clientSecret
  **/
  @JsonProperty("client_secret")
  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public FieldEmbedModelConfig clientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
    return this;
  }

 /**
   * Get projectId
   * @return projectId
  **/
  @JsonProperty("project_id")
  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public FieldEmbedModelConfig projectId(String projectId) {
    this.projectId = projectId;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FieldEmbedModelConfig {\n");
    
    sb.append("    modelName: ").append(toIndentedString(modelName)).append("\n");
    sb.append("    apiKey: ").append(toIndentedString(apiKey)).append("\n");
    sb.append("    accessToken: ").append(toIndentedString(accessToken)).append("\n");
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    clientSecret: ").append(toIndentedString(clientSecret)).append("\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
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
