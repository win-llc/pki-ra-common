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
 * AccountRestrictionForm
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-12T15:21:01.254Z[GMT]")
public class AccountRestrictionForm {
  @SerializedName("id")
  private Long id = null;

  @SerializedName("objectUuid")
  private String objectUuid = null;

  @SerializedName("objectClass")
  private String objectClass = null;

  @SerializedName("errors")
  private Map<String, String> errors = null;

  @SerializedName("accountId")
  private Long accountId = null;

  @SerializedName("type")
  private String type = null;

  @SerializedName("action")
  private String action = null;

  @SerializedName("dueBy")
  private String dueBy = null;

  @SerializedName("completed")
  private Boolean completed = null;

  @SerializedName("valid")
  private Boolean valid = null;

  @SerializedName("accountLinkedForm")
  private Boolean accountLinkedForm = null;

  @SerializedName("uniqueEntityForm")
  private Boolean uniqueEntityForm = null;

  public AccountRestrictionForm id(Long id) {
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

  public AccountRestrictionForm objectUuid(String objectUuid) {
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

  public AccountRestrictionForm objectClass(String objectClass) {
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

  public AccountRestrictionForm errors(Map<String, String> errors) {
    this.errors = errors;
    return this;
  }

  public AccountRestrictionForm putErrorsItem(String key, String errorsItem) {
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

  public AccountRestrictionForm accountId(Long accountId) {
    this.accountId = accountId;
    return this;
  }

   /**
   * Get accountId
   * @return accountId
  **/
  @Schema(description = "")
  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public AccountRestrictionForm type(String type) {
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

  public AccountRestrictionForm action(String action) {
    this.action = action;
    return this;
  }

   /**
   * Get action
   * @return action
  **/
  @Schema(description = "")
  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public AccountRestrictionForm dueBy(String dueBy) {
    this.dueBy = dueBy;
    return this;
  }

   /**
   * Get dueBy
   * @return dueBy
  **/
  @Schema(description = "")
  public String getDueBy() {
    return dueBy;
  }

  public void setDueBy(String dueBy) {
    this.dueBy = dueBy;
  }

  public AccountRestrictionForm completed(Boolean completed) {
    this.completed = completed;
    return this;
  }

   /**
   * Get completed
   * @return completed
  **/
  @Schema(description = "")
  public Boolean isCompleted() {
    return completed;
  }

  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  public AccountRestrictionForm valid(Boolean valid) {
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

  public AccountRestrictionForm accountLinkedForm(Boolean accountLinkedForm) {
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

  public AccountRestrictionForm uniqueEntityForm(Boolean uniqueEntityForm) {
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
    AccountRestrictionForm accountRestrictionForm = (AccountRestrictionForm) o;
    return Objects.equals(this.id, accountRestrictionForm.id) &&
        Objects.equals(this.objectUuid, accountRestrictionForm.objectUuid) &&
        Objects.equals(this.objectClass, accountRestrictionForm.objectClass) &&
        Objects.equals(this.errors, accountRestrictionForm.errors) &&
        Objects.equals(this.accountId, accountRestrictionForm.accountId) &&
        Objects.equals(this.type, accountRestrictionForm.type) &&
        Objects.equals(this.action, accountRestrictionForm.action) &&
        Objects.equals(this.dueBy, accountRestrictionForm.dueBy) &&
        Objects.equals(this.completed, accountRestrictionForm.completed) &&
        Objects.equals(this.valid, accountRestrictionForm.valid) &&
        Objects.equals(this.accountLinkedForm, accountRestrictionForm.accountLinkedForm) &&
        Objects.equals(this.uniqueEntityForm, accountRestrictionForm.uniqueEntityForm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, objectUuid, objectClass, errors, accountId, type, action, dueBy, completed, valid, accountLinkedForm, uniqueEntityForm);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountRestrictionForm {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectUuid: ").append(toIndentedString(objectUuid)).append("\n");
    sb.append("    objectClass: ").append(toIndentedString(objectClass)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    dueBy: ").append(toIndentedString(dueBy)).append("\n");
    sb.append("    completed: ").append(toIndentedString(completed)).append("\n");
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