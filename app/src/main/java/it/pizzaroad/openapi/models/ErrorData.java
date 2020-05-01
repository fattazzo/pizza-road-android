/*
 * Project: Pizza Road
 * File: ErrorData
 *
 * Created by fattazzo
 * Copyright © 2020 Gianluca Fattarsi. All rights reserved.
 *
 * MIT License
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/*
 * PizzaShop
 * <h1>REST API for managing a pizzeria.</h1> <br>  The application includes the management of:  <ul>     <li>users (workers and customers)</li>     <li>company branches</li>     <li>products (variations like doughs, dimensions and toppings, categories)</li>     <li>orders (creation, management)</li> <ul>
 *
 * OpenAPI spec version: 1.0.0
 * Contact: gianluca.fattarsi@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package it.pizzaroad.openapi.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * ErrorData
 */


public class ErrorData {
  @SerializedName("userTitle")
  private String userTitle = null;

  @SerializedName("userMessage")
  private String userMessage = null;

  @SerializedName("internal")
  private ErrorInternal internal = null;

  @SerializedName("constraintErrors")
  private List<ConstraintError> constraintErrors = null;

  public ErrorData userTitle(String userTitle) {
    this.userTitle = userTitle;
    return this;
  }

   /**
   * Get userTitle
   * @return userTitle
  **/
  @Schema(required = true, description = "")
  public String getUserTitle() {
    return userTitle;
  }

  public void setUserTitle(String userTitle) {
    this.userTitle = userTitle;
  }

  public ErrorData userMessage(String userMessage) {
    this.userMessage = userMessage;
    return this;
  }

   /**
   * Get userMessage
   * @return userMessage
  **/
  @Schema(required = true, description = "")
  public String getUserMessage() {
    return userMessage;
  }

  public void setUserMessage(String userMessage) {
    this.userMessage = userMessage;
  }

  public ErrorData internal(ErrorInternal internal) {
    this.internal = internal;
    return this;
  }

   /**
   * Get internal
   * @return internal
  **/
  @Schema(description = "")
  public ErrorInternal getInternal() {
    return internal;
  }

  public void setInternal(ErrorInternal internal) {
    this.internal = internal;
  }

  public ErrorData constraintErrors(List<ConstraintError> constraintErrors) {
    this.constraintErrors = constraintErrors;
    return this;
  }

  public ErrorData addConstraintErrorsItem(ConstraintError constraintErrorsItem) {
    if (this.constraintErrors == null) {
      this.constraintErrors = new ArrayList<ConstraintError>();
    }
    this.constraintErrors.add(constraintErrorsItem);
    return this;
  }

   /**
   * Get constraintErrors
   * @return constraintErrors
  **/
  @Schema(description = "")
  public List<ConstraintError> getConstraintErrors() {
    return constraintErrors;
  }

  public void setConstraintErrors(List<ConstraintError> constraintErrors) {
    this.constraintErrors = constraintErrors;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorData errorData = (ErrorData) o;
    return Objects.equals(this.userTitle, errorData.userTitle) &&
        Objects.equals(this.userMessage, errorData.userMessage) &&
        Objects.equals(this.internal, errorData.internal) &&
        Objects.equals(this.constraintErrors, errorData.constraintErrors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userTitle, userMessage, internal, constraintErrors);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorData {\n");
    
    sb.append("    userTitle: ").append(toIndentedString(userTitle)).append("\n");
    sb.append("    userMessage: ").append(toIndentedString(userMessage)).append("\n");
    sb.append("    internal: ").append(toIndentedString(internal)).append("\n");
    sb.append("    constraintErrors: ").append(toIndentedString(constraintErrors)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
