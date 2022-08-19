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
import org.threeten.bp.LocalDate;
/**
 * SecurityPolicyServerProjectDetails
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-12T15:21:01.254Z[GMT]")
public class SecurityPolicyServerProjectDetails {
  @SerializedName("projectId")
  private String projectId = null;

  @SerializedName("projectName")
  private String projectName = null;

  @SerializedName("validFrom")
  private LocalDate validFrom = null;

  @SerializedName("validTo")
  private LocalDate validTo = null;

  @SerializedName("fqdns")
  private List<String> fqdns = null;

  @SerializedName("allSecurityAttributesMap")
  private Map<String, Object> allSecurityAttributesMap = null;

  public SecurityPolicyServerProjectDetails projectId(String projectId) {
    this.projectId = projectId;
    return this;
  }

   /**
   * Get projectId
   * @return projectId
  **/
  @Schema(description = "")
  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public SecurityPolicyServerProjectDetails projectName(String projectName) {
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

  public SecurityPolicyServerProjectDetails validFrom(LocalDate validFrom) {
    this.validFrom = validFrom;
    return this;
  }

   /**
   * Get validFrom
   * @return validFrom
  **/
  @Schema(description = "")
  public LocalDate getValidFrom() {
    return validFrom;
  }

  public void setValidFrom(LocalDate validFrom) {
    this.validFrom = validFrom;
  }

  public SecurityPolicyServerProjectDetails validTo(LocalDate validTo) {
    this.validTo = validTo;
    return this;
  }

   /**
   * Get validTo
   * @return validTo
  **/
  @Schema(description = "")
  public LocalDate getValidTo() {
    return validTo;
  }

  public void setValidTo(LocalDate validTo) {
    this.validTo = validTo;
  }

  public SecurityPolicyServerProjectDetails fqdns(List<String> fqdns) {
    this.fqdns = fqdns;
    return this;
  }

  public SecurityPolicyServerProjectDetails addFqdnsItem(String fqdnsItem) {
    if (this.fqdns == null) {
      this.fqdns = new ArrayList<String>();
    }
    this.fqdns.add(fqdnsItem);
    return this;
  }

   /**
   * Get fqdns
   * @return fqdns
  **/
  @Schema(description = "")
  public List<String> getFqdns() {
    return fqdns;
  }

  public void setFqdns(List<String> fqdns) {
    this.fqdns = fqdns;
  }

  public SecurityPolicyServerProjectDetails allSecurityAttributesMap(Map<String, Object> allSecurityAttributesMap) {
    this.allSecurityAttributesMap = allSecurityAttributesMap;
    return this;
  }

  public SecurityPolicyServerProjectDetails putAllSecurityAttributesMapItem(String key, Object allSecurityAttributesMapItem) {
    if (this.allSecurityAttributesMap == null) {
      this.allSecurityAttributesMap = new HashMap<String, Object>();
    }
    this.allSecurityAttributesMap.put(key, allSecurityAttributesMapItem);
    return this;
  }

   /**
   * Get allSecurityAttributesMap
   * @return allSecurityAttributesMap
  **/
  @Schema(description = "")
  public Map<String, Object> getAllSecurityAttributesMap() {
    return allSecurityAttributesMap;
  }

  public void setAllSecurityAttributesMap(Map<String, Object> allSecurityAttributesMap) {
    this.allSecurityAttributesMap = allSecurityAttributesMap;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SecurityPolicyServerProjectDetails securityPolicyServerProjectDetails = (SecurityPolicyServerProjectDetails) o;
    return Objects.equals(this.projectId, securityPolicyServerProjectDetails.projectId) &&
        Objects.equals(this.projectName, securityPolicyServerProjectDetails.projectName) &&
        Objects.equals(this.validFrom, securityPolicyServerProjectDetails.validFrom) &&
        Objects.equals(this.validTo, securityPolicyServerProjectDetails.validTo) &&
        Objects.equals(this.fqdns, securityPolicyServerProjectDetails.fqdns) &&
        Objects.equals(this.allSecurityAttributesMap, securityPolicyServerProjectDetails.allSecurityAttributesMap);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, projectName, validFrom, validTo, fqdns, allSecurityAttributesMap);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SecurityPolicyServerProjectDetails {\n");
    
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    projectName: ").append(toIndentedString(projectName)).append("\n");
    sb.append("    validFrom: ").append(toIndentedString(validFrom)).append("\n");
    sb.append("    validTo: ").append(toIndentedString(validTo)).append("\n");
    sb.append("    fqdns: ").append(toIndentedString(fqdns)).append("\n");
    sb.append("    allSecurityAttributesMap: ").append(toIndentedString(allSecurityAttributesMap)).append("\n");
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