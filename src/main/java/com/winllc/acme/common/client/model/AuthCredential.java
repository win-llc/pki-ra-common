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
import org.threeten.bp.OffsetDateTime;
/**
 * AuthCredential
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-12T15:21:01.254Z[GMT]")
public class AuthCredential {
  @SerializedName("id")
  private Long id = null;

  @SerializedName("keyIdentifier")
  private String keyIdentifier = null;

  @SerializedName("macKey")
  private String macKey = null;

  @SerializedName("valid")
  private Boolean valid = null;

  @SerializedName("pocAssignedTo")
  private String pocAssignedTo = null;

  @SerializedName("createdOn")
  private OffsetDateTime createdOn = null;

  @SerializedName("expiresOn")
  private OffsetDateTime expiresOn = null;

  @SerializedName("parentEntity")
  private AuthCredentialHolder parentEntity = null;

  @SerializedName("macKeyBase64")
  private String macKeyBase64 = null;

  @SerializedName("new")
  private Boolean _new = null;

  public AuthCredential id(Long id) {
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

  public AuthCredential keyIdentifier(String keyIdentifier) {
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

  public AuthCredential macKey(String macKey) {
    this.macKey = macKey;
    return this;
  }

   /**
   * Get macKey
   * @return macKey
  **/
  @Schema(description = "")
  public String getMacKey() {
    return macKey;
  }

  public void setMacKey(String macKey) {
    this.macKey = macKey;
  }

  public AuthCredential valid(Boolean valid) {
    this.valid = valid;
    return this;
  }

   /**
   * Get valid
   * @return valid
  **/
  @Schema(description = "")
  public Boolean isValid() {
    return valid;
  }

  public void setValid(Boolean valid) {
    this.valid = valid;
  }

  public AuthCredential pocAssignedTo(String pocAssignedTo) {
    this.pocAssignedTo = pocAssignedTo;
    return this;
  }

   /**
   * Get pocAssignedTo
   * @return pocAssignedTo
  **/
  @Schema(description = "")
  public String getPocAssignedTo() {
    return pocAssignedTo;
  }

  public void setPocAssignedTo(String pocAssignedTo) {
    this.pocAssignedTo = pocAssignedTo;
  }

  public AuthCredential createdOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

   /**
   * Get createdOn
   * @return createdOn
  **/
  @Schema(description = "")
  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public AuthCredential expiresOn(OffsetDateTime expiresOn) {
    this.expiresOn = expiresOn;
    return this;
  }

   /**
   * Get expiresOn
   * @return expiresOn
  **/
  @Schema(description = "")
  public OffsetDateTime getExpiresOn() {
    return expiresOn;
  }

  public void setExpiresOn(OffsetDateTime expiresOn) {
    this.expiresOn = expiresOn;
  }

  public AuthCredential parentEntity(AuthCredentialHolder parentEntity) {
    this.parentEntity = parentEntity;
    return this;
  }

   /**
   * Get parentEntity
   * @return parentEntity
  **/
  @Schema(description = "")
  public AuthCredentialHolder getParentEntity() {
    return parentEntity;
  }

  public void setParentEntity(AuthCredentialHolder parentEntity) {
    this.parentEntity = parentEntity;
  }

  public AuthCredential macKeyBase64(String macKeyBase64) {
    this.macKeyBase64 = macKeyBase64;
    return this;
  }

   /**
   * Get macKeyBase64
   * @return macKeyBase64
  **/
  @Schema(description = "")
  public String getMacKeyBase64() {
    return macKeyBase64;
  }

  public void setMacKeyBase64(String macKeyBase64) {
    this.macKeyBase64 = macKeyBase64;
  }

  public AuthCredential _new(Boolean _new) {
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
    AuthCredential authCredential = (AuthCredential) o;
    return Objects.equals(this.id, authCredential.id) &&
        Objects.equals(this.keyIdentifier, authCredential.keyIdentifier) &&
        Objects.equals(this.macKey, authCredential.macKey) &&
        Objects.equals(this.valid, authCredential.valid) &&
        Objects.equals(this.pocAssignedTo, authCredential.pocAssignedTo) &&
        Objects.equals(this.createdOn, authCredential.createdOn) &&
        Objects.equals(this.expiresOn, authCredential.expiresOn) &&
        Objects.equals(this.parentEntity, authCredential.parentEntity) &&
        Objects.equals(this.macKeyBase64, authCredential.macKeyBase64) &&
        Objects.equals(this._new, authCredential._new);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, keyIdentifier, macKey, valid, pocAssignedTo, createdOn, expiresOn, parentEntity, macKeyBase64, _new);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthCredential {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    keyIdentifier: ").append(toIndentedString(keyIdentifier)).append("\n");
    sb.append("    macKey: ").append(toIndentedString(macKey)).append("\n");
    sb.append("    valid: ").append(toIndentedString(valid)).append("\n");
    sb.append("    pocAssignedTo: ").append(toIndentedString(pocAssignedTo)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    expiresOn: ").append(toIndentedString(expiresOn)).append("\n");
    sb.append("    parentEntity: ").append(toIndentedString(parentEntity)).append("\n");
    sb.append("    macKeyBase64: ").append(toIndentedString(macKeyBase64)).append("\n");
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
