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
 * AttributePolicyGroupForm
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-12T15:21:01.254Z[GMT]")
public class AttributePolicyGroupForm {
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

  @SerializedName("accountId")
  private Long accountId = null;

  @SerializedName("accountName")
  private String accountName = null;

  @SerializedName("attributePolicies")
  private List<AttributePolicy> attributePolicies = null;

  @SerializedName("valid")
  private Boolean valid = null;

  @SerializedName("accountLinkedForm")
  private Boolean accountLinkedForm = null;

  @SerializedName("uniqueEntityForm")
  private Boolean uniqueEntityForm = null;

  public AttributePolicyGroupForm id(Long id) {
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

  public AttributePolicyGroupForm objectUuid(String objectUuid) {
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

  public AttributePolicyGroupForm objectClass(String objectClass) {
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

  public AttributePolicyGroupForm errors(Map<String, String> errors) {
    this.errors = errors;
    return this;
  }

  public AttributePolicyGroupForm putErrorsItem(String key, String errorsItem) {
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

  public AttributePolicyGroupForm name(String name) {
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

  public AttributePolicyGroupForm accountId(Long accountId) {
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

  public AttributePolicyGroupForm accountName(String accountName) {
    this.accountName = accountName;
    return this;
  }

   /**
   * Get accountName
   * @return accountName
  **/
  @Schema(description = "")
  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public AttributePolicyGroupForm attributePolicies(List<AttributePolicy> attributePolicies) {
    this.attributePolicies = attributePolicies;
    return this;
  }

  public AttributePolicyGroupForm addAttributePoliciesItem(AttributePolicy attributePoliciesItem) {
    if (this.attributePolicies == null) {
      this.attributePolicies = new ArrayList<AttributePolicy>();
    }
    this.attributePolicies.add(attributePoliciesItem);
    return this;
  }

   /**
   * Get attributePolicies
   * @return attributePolicies
  **/
  @Schema(description = "")
  public List<AttributePolicy> getAttributePolicies() {
    return attributePolicies;
  }

  public void setAttributePolicies(List<AttributePolicy> attributePolicies) {
    this.attributePolicies = attributePolicies;
  }

  public AttributePolicyGroupForm valid(Boolean valid) {
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

  public AttributePolicyGroupForm accountLinkedForm(Boolean accountLinkedForm) {
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

  public AttributePolicyGroupForm uniqueEntityForm(Boolean uniqueEntityForm) {
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
    AttributePolicyGroupForm attributePolicyGroupForm = (AttributePolicyGroupForm) o;
    return Objects.equals(this.id, attributePolicyGroupForm.id) &&
        Objects.equals(this.objectUuid, attributePolicyGroupForm.objectUuid) &&
        Objects.equals(this.objectClass, attributePolicyGroupForm.objectClass) &&
        Objects.equals(this.errors, attributePolicyGroupForm.errors) &&
        Objects.equals(this.name, attributePolicyGroupForm.name) &&
        Objects.equals(this.accountId, attributePolicyGroupForm.accountId) &&
        Objects.equals(this.accountName, attributePolicyGroupForm.accountName) &&
        Objects.equals(this.attributePolicies, attributePolicyGroupForm.attributePolicies) &&
        Objects.equals(this.valid, attributePolicyGroupForm.valid) &&
        Objects.equals(this.accountLinkedForm, attributePolicyGroupForm.accountLinkedForm) &&
        Objects.equals(this.uniqueEntityForm, attributePolicyGroupForm.uniqueEntityForm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, objectUuid, objectClass, errors, name, accountId, accountName, attributePolicies, valid, accountLinkedForm, uniqueEntityForm);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AttributePolicyGroupForm {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectUuid: ").append(toIndentedString(objectUuid)).append("\n");
    sb.append("    objectClass: ").append(toIndentedString(objectClass)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    accountName: ").append(toIndentedString(accountName)).append("\n");
    sb.append("    attributePolicies: ").append(toIndentedString(attributePolicies)).append("\n");
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
