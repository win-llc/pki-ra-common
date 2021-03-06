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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * CertAuthorityConnectionInfoForm
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-12T15:21:01.254Z[GMT]")
public class CertAuthorityConnectionInfoForm {
  @SerializedName("id")
  private Long id = null;

  @SerializedName("objectUuid")
  private String objectUuid = null;

  @SerializedName("objectClass")
  private String objectClass = null;

  @SerializedName("errors")
  private Map<String, String> errors = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("type")
  private String type = null;

  @SerializedName("trustChainBase64")
  private String trustChainBase64 = null;

  @SerializedName("baseUrl")
  private String baseUrl = null;

  @SerializedName("properties")
  private List<CertAuthorityConnectionProperty> properties = null;

  @SerializedName("authKeyAlias")
  private String authKeyAlias = null;

  @SerializedName("valid")
  private Boolean valid = null;

  @SerializedName("accountLinkedForm")
  private Boolean accountLinkedForm = null;

  @SerializedName("uniqueEntityForm")
  private Boolean uniqueEntityForm = null;

  public CertAuthorityConnectionInfoForm id(Long id) {
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

  public CertAuthorityConnectionInfoForm objectUuid(String objectUuid) {
    this.objectUuid = objectUuid;
    return this;
  }

   /**
   * Get objectUuid
   * @return objectUuid
  **/
  @Schema(description = "")
  public String getObjectUuid() {
    return objectUuid;
  }

  public void setObjectUuid(String objectUuid) {
    this.objectUuid = objectUuid;
  }

  public CertAuthorityConnectionInfoForm objectClass(String objectClass) {
    this.objectClass = objectClass;
    return this;
  }

   /**
   * Get objectClass
   * @return objectClass
  **/
  @Schema(description = "")
  public String getObjectClass() {
    return objectClass;
  }

  public void setObjectClass(String objectClass) {
    this.objectClass = objectClass;
  }

  public CertAuthorityConnectionInfoForm errors(Map<String, String> errors) {
    this.errors = errors;
    return this;
  }

  public CertAuthorityConnectionInfoForm putErrorsItem(String key, String errorsItem) {
    if (this.errors == null) {
      this.errors = new HashMap<String, String>();
    }
    this.errors.put(key, errorsItem);
    return this;
  }

   /**
   * Get errors
   * @return errors
  **/
  @Schema(description = "")
  public Map<String, String> getErrors() {
    return errors;
  }

  public void setErrors(Map<String, String> errors) {
    this.errors = errors;
  }

  public CertAuthorityConnectionInfoForm name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @Schema(description = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CertAuthorityConnectionInfoForm type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @Schema(description = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public CertAuthorityConnectionInfoForm trustChainBase64(String trustChainBase64) {
    this.trustChainBase64 = trustChainBase64;
    return this;
  }

   /**
   * Get trustChainBase64
   * @return trustChainBase64
  **/
  @Schema(description = "")
  public String getTrustChainBase64() {
    return trustChainBase64;
  }

  public void setTrustChainBase64(String trustChainBase64) {
    this.trustChainBase64 = trustChainBase64;
  }

  public CertAuthorityConnectionInfoForm baseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
    return this;
  }

   /**
   * Get baseUrl
   * @return baseUrl
  **/
  @Schema(description = "")
  public String getBaseUrl() {
    return baseUrl;
  }

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public CertAuthorityConnectionInfoForm properties(List<CertAuthorityConnectionProperty> properties) {
    this.properties = properties;
    return this;
  }

  public CertAuthorityConnectionInfoForm addPropertiesItem(CertAuthorityConnectionProperty propertiesItem) {
    if (this.properties == null) {
      this.properties = new ArrayList<CertAuthorityConnectionProperty>();
    }
    this.properties.add(propertiesItem);
    return this;
  }

   /**
   * Get properties
   * @return properties
  **/
  @Schema(description = "")
  public List<CertAuthorityConnectionProperty> getProperties() {
    return properties;
  }

  public void setProperties(List<CertAuthorityConnectionProperty> properties) {
    this.properties = properties;
  }

  public CertAuthorityConnectionInfoForm authKeyAlias(String authKeyAlias) {
    this.authKeyAlias = authKeyAlias;
    return this;
  }

   /**
   * Get authKeyAlias
   * @return authKeyAlias
  **/
  @Schema(description = "")
  public String getAuthKeyAlias() {
    return authKeyAlias;
  }

  public void setAuthKeyAlias(String authKeyAlias) {
    this.authKeyAlias = authKeyAlias;
  }

  public CertAuthorityConnectionInfoForm valid(Boolean valid) {
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

  public CertAuthorityConnectionInfoForm accountLinkedForm(Boolean accountLinkedForm) {
    this.accountLinkedForm = accountLinkedForm;
    return this;
  }

   /**
   * Get accountLinkedForm
   * @return accountLinkedForm
  **/
  @Schema(description = "")
  public Boolean isAccountLinkedForm() {
    return accountLinkedForm;
  }

  public void setAccountLinkedForm(Boolean accountLinkedForm) {
    this.accountLinkedForm = accountLinkedForm;
  }

  public CertAuthorityConnectionInfoForm uniqueEntityForm(Boolean uniqueEntityForm) {
    this.uniqueEntityForm = uniqueEntityForm;
    return this;
  }

   /**
   * Get uniqueEntityForm
   * @return uniqueEntityForm
  **/
  @Schema(description = "")
  public Boolean isUniqueEntityForm() {
    return uniqueEntityForm;
  }

  public void setUniqueEntityForm(Boolean uniqueEntityForm) {
    this.uniqueEntityForm = uniqueEntityForm;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CertAuthorityConnectionInfoForm certAuthorityConnectionInfoForm = (CertAuthorityConnectionInfoForm) o;
    return Objects.equals(this.id, certAuthorityConnectionInfoForm.id) &&
        Objects.equals(this.objectUuid, certAuthorityConnectionInfoForm.objectUuid) &&
        Objects.equals(this.objectClass, certAuthorityConnectionInfoForm.objectClass) &&
        Objects.equals(this.errors, certAuthorityConnectionInfoForm.errors) &&
        Objects.equals(this.name, certAuthorityConnectionInfoForm.name) &&
        Objects.equals(this.type, certAuthorityConnectionInfoForm.type) &&
        Objects.equals(this.trustChainBase64, certAuthorityConnectionInfoForm.trustChainBase64) &&
        Objects.equals(this.baseUrl, certAuthorityConnectionInfoForm.baseUrl) &&
        Objects.equals(this.properties, certAuthorityConnectionInfoForm.properties) &&
        Objects.equals(this.authKeyAlias, certAuthorityConnectionInfoForm.authKeyAlias) &&
        Objects.equals(this.valid, certAuthorityConnectionInfoForm.valid) &&
        Objects.equals(this.accountLinkedForm, certAuthorityConnectionInfoForm.accountLinkedForm) &&
        Objects.equals(this.uniqueEntityForm, certAuthorityConnectionInfoForm.uniqueEntityForm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, objectUuid, objectClass, errors, name, type, trustChainBase64, baseUrl, properties, authKeyAlias, valid, accountLinkedForm, uniqueEntityForm);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CertAuthorityConnectionInfoForm {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectUuid: ").append(toIndentedString(objectUuid)).append("\n");
    sb.append("    objectClass: ").append(toIndentedString(objectClass)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    trustChainBase64: ").append(toIndentedString(trustChainBase64)).append("\n");
    sb.append("    baseUrl: ").append(toIndentedString(baseUrl)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
    sb.append("    authKeyAlias: ").append(toIndentedString(authKeyAlias)).append("\n");
    sb.append("    valid: ").append(toIndentedString(valid)).append("\n");
    sb.append("    accountLinkedForm: ").append(toIndentedString(accountLinkedForm)).append("\n");
    sb.append("    uniqueEntityForm: ").append(toIndentedString(uniqueEntityForm)).append("\n");
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
