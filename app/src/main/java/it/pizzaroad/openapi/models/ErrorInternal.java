/*
 * Project: Pizza Road
 * File: ErrorInternal
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

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * ErrorInternal
 */


public class ErrorInternal {
  @SerializedName("exception")
  private String exception = null;

  @SerializedName("stack")
  private String stack = null;

  public ErrorInternal exception(String exception) {
    this.exception = exception;
    return this;
  }

   /**
   * Get exception
   * @return exception
  **/
  @Schema(required = true, description = "")
  public String getException() {
    return exception;
  }

  public void setException(String exception) {
    this.exception = exception;
  }

  public ErrorInternal stack(String stack) {
    this.stack = stack;
    return this;
  }

   /**
   * Get stack
   * @return stack
  **/
  @Schema(description = "")
  public String getStack() {
    return stack;
  }

  public void setStack(String stack) {
    this.stack = stack;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorInternal errorInternal = (ErrorInternal) o;
    return Objects.equals(this.exception, errorInternal.exception) &&
        Objects.equals(this.stack, errorInternal.stack);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exception, stack);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorInternal {\n");
    
    sb.append("    exception: ").append(toIndentedString(exception)).append("\n");
    sb.append("    stack: ").append(toIndentedString(stack)).append("\n");
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
