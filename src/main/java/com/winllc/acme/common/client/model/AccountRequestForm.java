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

import java.util.HashMap;
import java.util.Map;
/**
 * AccountRequestForm
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-12T15:21:01.254Z[GMT]")
public class AccountRequestForm {
  @SerializedName("id")
  private Long id = null;

  @SerializedName("objectUuid")
  private String objectUuid = null;

  @SerializedName("objectClass")
  private String objectClass = null;

  @SerializedName("errors")
  private Map<String, String> errors = null;

  @SerializedName("accountOwnerEmail")
  private String accountOwnerEmail = null;

  @SerializedName("projectName")
  private String projectName = null;

  @SerializedName("securityPolicyServerProjectId")
  private String securityPolicyServerProjectId = null;

  @SerializedName("valid")
  private Boolean valid = null;

  @SerializedName("accountLinkedForm")
  private Boolean accountLinkedForm = null;

  @SerializedName("uniqueEntityForm")
  private Boolean uniqueEntityForm = null;

  public AccountRequestForm id(Long id) {
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

  public AccountRequestForm objectUuid(String objectUuid) {
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

  public AccountRequestForm objectClass(String objectClass) {
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

  public AccountRequestForm errors(Map<String, String> errors) {
    this.errors = errors;
    return this;
  }

  public AccountRequestForm putErrorsItem(String key, String errorsItem) {
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

  public AccountRequestForm accountOwnerEmail(String accountOwnerEmail) {
    this.accountOwnerEmail = accountOwnerEmail;
    return this;
  }

   /**
   * Get accountOwnerEmail
   * @return accountOwnerEmail
  **/
  @Schema(required = true, description = "")
  public String getAccountOwnerEmail() {
    return accountOwnerEmail;
  }

  public void setAccountOwnerEmail(String accountOwnerEmail) {
    this.accountOwnerEmail = accountOwnerEmail;
  }

  public AccountRequestForm projectName(String projectName) {
    this.projectName = projectName;
    return this;
  }

   /**
   * Get projectName
   * @return projectName
  **/
  @Schema(required = true, description = "")
  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public AccountRequestForm securityPolicyServerProjectId(String securityPolicyServerProjectId) {
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

  public AccountRequestForm valid(Boolean valid) {
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

  public AccountRequestForm accountLinkedForm(Boolean accountLinkedForm) {
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

  public AccountRequestForm uniqueEntityForm(Boolean uniqueEntityForm) {
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
    AccountRequestForm accountRequestForm = (AccountRequestForm) o;
    return Objects.equals(this.id, accountRequestForm.id) &&
        Objects.equals(this.objectUuid, accountRequestForm.objectUuid) &&
        Objects.equals(this.objectClass, accountRequestForm.objectClass) &&
        Objects.equals(this.errors, accountRequestForm.errors) &&
        Objects.equals(this.accountOwnerEmail, accountRequestForm.accountOwnerEmail) &&
        Objects.equals(this.projectName, accountRequestForm.projectName) &&
        Objects.equals(this.securityPolicyServerProjectId, accountRequestForm.securityPolicyServerProjectId) &&
        Objects.equals(this.valid, accountRequestForm.valid) &&
        Objects.equals(this.accountLinkedForm, accountRequestForm.accountLinkedForm) &&
        Objects.equals(this.uniqueEntityForm, accountRequestForm.uniqueEntityForm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, objectUuid, objectClass, errors, accountOwnerEmail, projectName, securityPolicyServerProjectId, valid, accountLinkedForm, uniqueEntityForm);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountRequestForm {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectUuid: ").append(toIndentedString(objectUuid)).append("\n");
    sb.append("    objectClass: ").append(toIndentedString(objectClass)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("    accountOwnerEmail: ").append(toIndentedString(accountOwnerEmail)).append("\n");
    sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
    sb.append("    securityPolicyServerProjectId: ").append(toIndentedString(securityPolicyServerProjectId)).append("\n");
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
