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
 * DomainLinkToAccountRequestInfo
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-12T15:21:01.254Z[GMT]")
public class DomainLinkToAccountRequestInfo {
  @SerializedName("id")
  private Long id = null;

  @SerializedName("objectUuid")
  private String objectUuid = null;

  @SerializedName("objectClass")
  private String objectClass = null;

  @SerializedName("status")
  private String status = null;

  @SerializedName("domainInfoList")
  private List<DomainInfo> domainInfoList = null;

  @SerializedName("accountInfo")
  private AccountInfo accountInfo = null;

  @SerializedName("uniqueEntityForm")
  private Boolean uniqueEntityForm = null;

  public DomainLinkToAccountRequestInfo id(Long id) {
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

  public DomainLinkToAccountRequestInfo objectUuid(String objectUuid) {
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

  public DomainLinkToAccountRequestInfo objectClass(String objectClass) {
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

  public DomainLinkToAccountRequestInfo status(String status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @Schema(description = "")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public DomainLinkToAccountRequestInfo domainInfoList(List<DomainInfo> domainInfoList) {
    this.domainInfoList = domainInfoList;
    return this;
  }

  public DomainLinkToAccountRequestInfo addDomainInfoListItem(DomainInfo domainInfoListItem) {
    if (this.domainInfoList == null) {
      this.domainInfoList = new ArrayList<DomainInfo>();
    }
    this.domainInfoList.add(domainInfoListItem);
    return this;
  }

   /**
   * Get domainInfoList
   * @return domainInfoList
  **/
  @Schema(description = "")
  public List<DomainInfo> getDomainInfoList() {
    return domainInfoList;
  }

  public void setDomainInfoList(List<DomainInfo> domainInfoList) {
    this.domainInfoList = domainInfoList;
  }

  public DomainLinkToAccountRequestInfo accountInfo(AccountInfo accountInfo) {
    this.accountInfo = accountInfo;
    return this;
  }

   /**
   * Get accountInfo
   * @return accountInfo
  **/
  @Schema(description = "")
  public AccountInfo getAccountInfo() {
    return accountInfo;
  }

  public void setAccountInfo(AccountInfo accountInfo) {
    this.accountInfo = accountInfo;
  }

  public DomainLinkToAccountRequestInfo uniqueEntityForm(Boolean uniqueEntityForm) {
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
    DomainLinkToAccountRequestInfo domainLinkToAccountRequestInfo = (DomainLinkToAccountRequestInfo) o;
    return Objects.equals(this.id, domainLinkToAccountRequestInfo.id) &&
        Objects.equals(this.objectUuid, domainLinkToAccountRequestInfo.objectUuid) &&
        Objects.equals(this.objectClass, domainLinkToAccountRequestInfo.objectClass) &&
        Objects.equals(this.status, domainLinkToAccountRequestInfo.status) &&
        Objects.equals(this.domainInfoList, domainLinkToAccountRequestInfo.domainInfoList) &&
        Objects.equals(this.accountInfo, domainLinkToAccountRequestInfo.accountInfo) &&
        Objects.equals(this.uniqueEntityForm, domainLinkToAccountRequestInfo.uniqueEntityForm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, objectUuid, objectClass, status, domainInfoList, accountInfo, uniqueEntityForm);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DomainLinkToAccountRequestInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectUuid: ").append(toIndentedString(objectUuid)).append("\n");
    sb.append("    objectClass: ").append(toIndentedString(objectClass)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    domainInfoList: ").append(toIndentedString(domainInfoList)).append("\n");
    sb.append("    accountInfo: ").append(toIndentedString(accountInfo)).append("\n");
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
