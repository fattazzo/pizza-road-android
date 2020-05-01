/*
 * Project: Pizza Road
 * File: ProductCategoryDetails
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
 * ProductCategoryDetails
 */


public class ProductCategoryDetails extends ProductCategory {
  @SerializedName("doughs")
  private List<VariationDough> doughs = null;

  @SerializedName("sizes")
  private List<VariationSize> sizes = null;

  public ProductCategoryDetails doughs(List<VariationDough> doughs) {
    this.doughs = doughs;
    return this;
  }

  public ProductCategoryDetails addDoughsItem(VariationDough doughsItem) {
    if (this.doughs == null) {
      this.doughs = new ArrayList<VariationDough>();
    }
    this.doughs.add(doughsItem);
    return this;
  }

   /**
   * Get doughs
   * @return doughs
  **/
  @Schema(description = "")
  public List<VariationDough> getDoughs() {
    return doughs;
  }

  public void setDoughs(List<VariationDough> doughs) {
    this.doughs = doughs;
  }

  public ProductCategoryDetails sizes(List<VariationSize> sizes) {
    this.sizes = sizes;
    return this;
  }

  public ProductCategoryDetails addSizesItem(VariationSize sizesItem) {
    if (this.sizes == null) {
      this.sizes = new ArrayList<VariationSize>();
    }
    this.sizes.add(sizesItem);
    return this;
  }

   /**
   * Get sizes
   * @return sizes
  **/
  @Schema(description = "")
  public List<VariationSize> getSizes() {
    return sizes;
  }

  public void setSizes(List<VariationSize> sizes) {
    this.sizes = sizes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductCategoryDetails productCategoryDetails = (ProductCategoryDetails) o;
    return Objects.equals(this.doughs, productCategoryDetails.doughs) &&
        Objects.equals(this.sizes, productCategoryDetails.sizes) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(doughs, sizes, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductCategoryDetails {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    doughs: ").append(toIndentedString(doughs)).append("\n");
    sb.append("    sizes: ").append(toIndentedString(sizes)).append("\n");
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
