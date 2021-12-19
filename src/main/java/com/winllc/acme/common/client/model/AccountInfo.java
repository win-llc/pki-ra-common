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
import java.util.List;
/**
 * AccountInfo
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-12T15:21:01.254Z[GMT]")
public class AccountInfo {
  @SerializedName("id")
  private Long id = null;

  @SerializedName("objectUuid")
  private String objectUuid = null;

  @SerializedName("objectClass")
  private String objectClass = null;

  @SerializedName("keyIdentifier")
  private String keyIdentifier = null;

  @SerializedName("macKey")
  private String macKey = null;

  @SerializedName("macKeyBase64")
  private String macKeyBase64 = null;

  @SerializedName("projectName")
  private String projectName = null;

  @SerializedName("entityBaseDn")
  private String entityBaseDn = null;

  @SerializedName("acmeRequireHttpValidation")
  private Boolean acmeRequireHttpValidation = null;

  @SerializedName("enabled")
  private Boolean enabled = null;

  @SerializedName("securityPolicyServerProjectId")
  private String securityPolicyServerProjectId = null;

  @SerializedName("accountOwner")
  private UserInfo accountOwner = null;

  @SerializedName("pocs")
  private List<UserInfo> pocs = null;

  @SerializedName("canIssueDomains")
  private List<DomainInfo> canIssueDomains = null;

  @SerializedName("acmeConnectionInfoList")
  private List<AcmeConnectionInfo> acmeConnectionInfoList = null;

  @SerializedName("uniqueEntityForm")
  private Boolean uniqueEntityForm = null;

  public AccountInfo id(Long id) {
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

  public AccountInfo objectUuid(String objectUuid) {
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

  public AccountInfo objectClass(String objectClass) {
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

  public AccountInfo keyIdentifier(String keyIdentifier) {
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

  public AccountInfo macKey(String macKey) {
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

  public AccountInfo macKeyBase64(String macKeyBase64) {
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

  public AccountInfo projectName(String projectName) {
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

  public AccountInfo entityBaseDn(String entityBaseDn) {
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

  public AccountInfo acmeRequireHttpValidation(Boolean acmeRequireHttpValidation) {
    this.acmeRequireHttpValidation = acmeRequireHttpValidation;
    return this;
  }

   /**
   * Get acmeRequireHttpValidation
   * @return acmeRequireHttpValidation
  **/
  @Schema(description = "")
  public Boolean isAcmeRequireHttpValidation() {
    return acmeRequireHttpValidation;
  }

  public void setAcmeRequireHttpValidation(Boolean acmeRequireHttpValidation) {
    this.acmeRequireHttpValidation = acmeRequireHttpValidation;
  }

  public AccountInfo enabled(Boolean enabled) {
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

  public AccountInfo securityPolicyServerProjectId(String securityPolicyServerProjectId) {
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

  public AccountInfo accountOwner(UserInfo accountOwner) {
    this.accountOwner = accountOwner;
    return this;
  }

   /**
   * Get accountOwner
   * @return accountOwner
  **/
  @Schema(description = "")
  public UserInfo getAccountOwner() {
    return accountOwner;
  }

  public void setAccountOwner(UserInfo accountOwner) {
    this.accountOwner = accountOwner;
  }

  public AccountInfo pocs(List<UserInfo> pocs) {
    this.pocs = pocs;
    return this;
  }

  public AccountInfo addPocsItem(UserInfo pocsItem) {
    if (this.pocs == null) {
      this.pocs = new ArrayList<UserInfo>();
    }
    this.pocs.add(pocsItem);
    return this;
  }

   /**
   * Get pocs
   * @return pocs
  **/
  @Schema(description = "")
  public List<UserInfo> getPocs() {
    return pocs;
  }

  public void setPocs(List<UserInfo> pocs) {
    this.pocs = pocs;
  }

  public AccountInfo canIssueDomains(List<DomainInfo> canIssueDomains) {
    this.canIssueDomains = canIssueDomains;
    return this;
  }

  public AccountInfo addCanIssueDomainsItem(DomainInfo canIssueDomainsItem) {
    if (this.canIssueDomains == null) {
      this.canIssueDomains = new ArrayList<DomainInfo>();
    }
    this.canIssueDomains.add(canIssueDomainsItem);
    return this;
  }

   /**
   * Get canIssueDomains
   * @return canIssueDomains
  **/
  @Schema(description = "")
  public List<DomainInfo> getCanIssueDomains() {
    return canIssueDomains;
  }

  public void setCanIssueDomains(List<DomainInfo> canIssueDomains) {
    this.canIssueDomains = canIssueDomains;
  }

  public AccountInfo acmeConnectionInfoList(List<AcmeConnectionInfo> acmeConnectionInfoList) {
    this.acmeConnectionInfoList = acmeConnectionInfoList;
    return this;
  }

  public AccountInfo addAcmeConnectionInfoListItem(AcmeConnectionInfo acmeConnectionInfoListItem) {
    if (this.acmeConnectionInfoList == null) {
      this.acmeConnectionInfoList = new ArrayList<AcmeConnectionInfo>();
    }
    this.acmeConnectionInfoList.add(acmeConnectionInfoListItem);
    return this;
  }

   /**
   * Get acmeConnectionInfoList
   * @return acmeConnectionInfoList
  **/
  @Schema(description = "")
  public List<AcmeConnectionInfo> getAcmeConnectionInfoList() {
    return acmeConnectionInfoList;
  }

  public void setAcmeConnectionInfoList(List<AcmeConnectionInfo> acmeConnectionInfoList) {
    this.acmeConnectionInfoList = acmeConnectionInfoList;
  }

  public AccountInfo uniqueEntityForm(Boolean uniqueEntityForm) {
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
    AccountInfo accountInfo = (AccountInfo) o;
    return Objects.equals(this.id, accountInfo.id) &&
        Objects.equals(this.objectUuid, accountInfo.objectUuid) &&
        Objects.equals(this.objectClass, accountInfo.objectClass) &&
        Objects.equals(this.keyIdentifier, accountInfo.keyIdentifier) &&
        Objects.equals(this.macKey, accountInfo.macKey) &&
        Objects.equals(this.macKeyBase64, accountInfo.macKeyBase64) &&
        Objects.equals(this.projectName, accountInfo.projectName) &&
        Objects.equals(this.entityBaseDn, accountInfo.entityBaseDn) &&
        Objects.equals(this.acmeRequireHttpValidation, accountInfo.acmeRequireHttpValidation) &&
        Objects.equals(this.enabled, accountInfo.enabled) &&
        Objects.equals(this.securityPolicyServerProjectId, accountInfo.securityPolicyServerProjectId) &&
        Objects.equals(this.accountOwner, accountInfo.accountOwner) &&
        Objects.equals(this.pocs, accountInfo.pocs) &&
        Objects.equals(this.canIssueDomains, accountInfo.canIssueDomains) &&
        Objects.equals(this.acmeConnectionInfoList, accountInfo.acmeConnectionInfoList) &&
        Objects.equals(this.uniqueEntityForm, accountInfo.uniqueEntityForm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, objectUuid, objectClass, keyIdentifier, macKey, macKeyBase64, projectName, entityBaseDn, acmeRequireHttpValidation, enabled, securityPolicyServerProjectId, accountOwner, pocs, canIssueDomains, acmeConnectionInfoList, uniqueEntityForm);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectUuid: ").append(toIndentedString(objectUuid)).append("\n");
    sb.append("    objectClass: ").append(toIndentedString(objectClass)).append("\n");
    sb.append("    keyIdentifier: ").append(toIndentedString(keyIdentifier)).append("\n");
    sb.append("    macKey: ").append(toIndentedString(macKey)).append("\n");
    sb.append("    macKeyBase64: ").append(toIndentedString(macKeyBase64)).append("\n");
    sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
    sb.append("    entityBaseDn: ").append(toIndentedString(entityBaseDn)).append("\n");
    sb.append("    acmeRequireHttpValidation: ").append(toIndentedString(acmeRequireHttpValidation)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
    sb.append("    securityPolicyServerProjectId: ").append(toIndentedString(securityPolicyServerProjectId)).append("\n");
    sb.append("    accountOwner: ").append(toIndentedString(accountOwner)).append("\n");
    sb.append("    pocs: ").append(toIndentedString(pocs)).append("\n");
    sb.append("    canIssueDomains: ").append(toIndentedString(canIssueDomains)).append("\n");
    sb.append("    acmeConnectionInfoList: ").append(toIndentedString(acmeConnectionInfoList)).append("\n");
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
