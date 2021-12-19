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

/**
 * UniqueEntityLookupForm
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-12T15:21:01.254Z[GMT]")
public class UniqueEntityLookupForm {
  @SerializedName("objectClass")
  private String objectClass = null;

  @SerializedName("objectUuid")
  private String objectUuid = null;

  public UniqueEntityLookupForm objectClass(String objectClass) {
    this.objectClass = objectClass;
    return this;
  }

   /**
   * Get objectClass
   * @return objectClass
  **/
  @Schema(required = true, description = "")
  public String getObjectClass() {
    return objectClass;
  }

  public void setObjectClass(String objectClass) {
    this.objectClass = objectClass;
  }

  public UniqueEntityLookupForm objectUuid(String objectUuid) {
    this.objectUuid = objectUuid;
    return this;
  }

   /**
   * Get objectUuid
   * @return objectUuid
  **/
  @Schema(required = true, description = "")
  public String getObjectUuid() {
    return objectUuid;
  }

  public void setObjectUuid(String objectUuid) {
    this.objectUuid = objectUuid;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UniqueEntityLookupForm uniqueEntityLookupForm = (UniqueEntityLookupForm) o;
    return Objects.equals(this.objectClass, uniqueEntityLookupForm.objectClass) &&
        Objects.equals(this.objectUuid, uniqueEntityLookupForm.objectUuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectClass, objectUuid);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UniqueEntityLookupForm {\n");
    
    sb.append("    objectClass: ").append(toIndentedString(objectClass)).append("\n");
    sb.append("    objectUuid: ").append(toIndentedString(objectUuid)).append("\n");
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
