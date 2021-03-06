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
 * KeyEntryWrapper
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-12T15:21:01.254Z[GMT]")
public class KeyEntryWrapper {
  @SerializedName("alias")
  private String alias = null;

  public KeyEntryWrapper alias(String alias) {
    this.alias = alias;
    return this;
  }

   /**
   * Get alias
   * @return alias
  **/
  @Schema(description = "")
  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeyEntryWrapper keyEntryWrapper = (KeyEntryWrapper) o;
    return Objects.equals(this.alias, keyEntryWrapper.alias);
  }

  @Override
  public int hashCode() {
    return Objects.hash(alias);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class KeyEntryWrapper {\n");
    
    sb.append("    alias: ").append(toIndentedString(alias)).append("\n");
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
