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
 * EstServerProperties
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-12T15:21:01.254Z[GMT]")
public class EstServerProperties {
  @SerializedName("id")
  private Long id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("caConnectionName")
  private String caConnectionName = null;

  @SerializedName("new")
  private Boolean _new = null;

  public EstServerProperties id(Long id) {
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

  public EstServerProperties name(String name) {
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

  public EstServerProperties caConnectionName(String caConnectionName) {
    this.caConnectionName = caConnectionName;
    return this;
  }

   /**
   * Get caConnectionName
   * @return caConnectionName
  **/
  @Schema(description = "")
  public String getCaConnectionName() {
    return caConnectionName;
  }

  public void setCaConnectionName(String caConnectionName) {
    this.caConnectionName = caConnectionName;
  }

  public EstServerProperties _new(Boolean _new) {
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
    EstServerProperties estServerProperties = (EstServerProperties) o;
    return Objects.equals(this.id, estServerProperties.id) &&
        Objects.equals(this.name, estServerProperties.name) &&
        Objects.equals(this.caConnectionName, estServerProperties.caConnectionName) &&
        Objects.equals(this._new, estServerProperties._new);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, caConnectionName, _new);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EstServerProperties {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    caConnectionName: ").append(toIndentedString(caConnectionName)).append("\n");
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