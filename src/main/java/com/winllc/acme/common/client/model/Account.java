/*
 * OpenAPI definition
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.winllc.acme.common.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;
/**
 * Account
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-12T15:21:01.254Z[GMT]")
public class Account {
  @SerializedName("id")
  private Long id = null;

  @SerializedName("uuid")
  private UUID uuid = null;

  @SerializedName("keyIdentifier")
  private String keyIdentifier = null;

  @SerializedName("projectName")
  private String projectName = null;

  @SerializedName("entityBaseDn")
  private String entityBaseDn = null;

  @SerializedName("enabled")
  private Boolean enabled = null;

  @SerializedName("securityPolicyServerProjectId")
  private String securityPolicyServerProjectId = null;

  @SerializedName("allowHostnameIssuance")
  private Boolean allowHostnameIssuance = null;

  @SerializedName("new")
  private Boolean _new = null;

  public Account id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @Schema(description = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Account uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

   /**
   * Get uuid
   * @return uuid
  **/
  @Schema(description = "")
  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public Account keyIdentifier(String keyIdentifier) {
    this.keyIdentifier = keyIdentifier;
    return this;
  }

   /**
   * Get keyIdentifier
   * @return keyIdentifier
  **/
  @Schema(description = "")
  public String getKeyIdentifier() {
    return keyIdentifier;
  }

  public void setKeyIdentifier(String keyIdentifier) {
    this.keyIdentifier = keyIdentifier;
  }

  public Account projectName(String projectName) {
    this.projectName = projectName;
    return this;
  }

   /**
   * Get projectName
   * @return projectName
  **/
  @Schema(description = "")
  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public Account entityBaseDn(String entityBaseDn) {
    this.entityBaseDn = entityBaseDn;
    return this;
  }

   /**
   * Get entityBaseDn
   * @return entityBaseDn
  **/
  @Schema(description = "")
  public String getEntityBaseDn() {
    return entityBaseDn;
  }

  public void setEntityBaseDn(String entityBaseDn) {
    this.entityBaseDn = entityBaseDn;
  }

  public Account enabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

   /**
   * Get enabled
   * @return enabled
  **/
  @Schema(description = "")
  public Boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Account securityPolicyServerProjectId(String securityPolicyServerProjectId) {
    this.securityPolicyServerProjectId = securityPolicyServerProjectId;
    return this;
  }

   /**
   * Get securityPolicyServerProjectId
   * @return securityPolicyServerProjectId
  **/
  @Schema(description = "")
  public String getSecurityPolicyServerProjectId() {
    return securityPolicyServerProjectId;
  }

  public void setSecurityPolicyServerProjectId(String securityPolicyServerProjectId) {
    this.securityPolicyServerProjectId = securityPolicyServerProjectId;
  }

  public Account allowHostnameIssuance(Boolean allowHostnameIssuance) {
    this.allowHostnameIssuance = allowHostnameIssuance;
    return this;
  }

   /**
   * Get allowHostnameIssuance
   * @return allowHostnameIssuance
  **/
  @Schema(description = "")
  public Boolean isAllowHostnameIssuance() {
    return allowHostnameIssuance;
  }

  public void setAllowHostnameIssuance(Boolean allowHostnameIssuance) {
    this.allowHostnameIssuance = allowHostnameIssuance;
  }

  public Account _new(Boolean _new) {
    this._new = _new;
    return this;
  }

   /**
   * Get _new
   * @return _new
  **/
  @Schema(description = "")
  public Boolean isNew() {
    return _new;
  }

  public void setNew(Boolean _new) {
    this._new = _new;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.id, account.id) &&
        Objects.equals(this.uuid, account.uuid) &&
        Objects.equals(this.keyIdentifier, account.keyIdentifier) &&
        Objects.equals(this.projectName, account.projectName) &&
        Objects.equals(this.entityBaseDn, account.entityBaseDn) &&
        Objects.equals(this.enabled, account.enabled) &&
        Objects.equals(this.securityPolicyServerProjectId, account.securityPolicyServerProjectId) &&
        Objects.equals(this.allowHostnameIssuance, account.allowHostnameIssuance) &&
        Objects.equals(this._new, account._new);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, keyIdentifier, projectName, entityBaseDn, enabled, securityPolicyServerProjectId, allowHostnameIssuance, _new);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    keyIdentifier: ").append(toIndentedString(keyIdentifier)).append("\n");
    sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
    sb.append("    entityBaseDn: ").append(toIndentedString(entityBaseDn)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
    sb.append("    securityPolicyServerProjectId: ").append(toIndentedString(securityPolicyServerProjectId)).append("\n");
    sb.append("    allowHostnameIssuance: ").append(toIndentedString(allowHostnameIssuance)).append("\n");
    sb.append("    _new: ").append(toIndentedString(_new)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
