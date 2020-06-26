/*
 * Project: Pizza Road
 * File: Item
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Item
 */


public class Item {
  @SerializedName("id")
  private Integer id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("enabled")
  private Boolean enabled = null;

  @SerializedName("category")
  private Category category = null;

  @SerializedName("imageUrl")
  private String imageUrl = null;

  @SerializedName("availablePrices")
  private List<BigDecimal> availablePrices = null;

  public Item id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier
   * @return id
  **/
  @Schema(description = "Unique identifier")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Item name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @Schema(required = true, description = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Item description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @Schema(description = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Item enabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

   /**
   * Get enabled
   * @return enabled
  **/
  @Schema(required = true, description = "")
  public Boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Item category(Category category) {
    this.category = category;
    return this;
  }

   /**
   * Get category
   * @return category
  **/
  @Schema(required = true, description = "")
  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Item imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

   /**
   * Get imageUrl
   * @return imageUrl
  **/
  @Schema(description = "")
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

   /**
   * Get availablePrices
   * @return availablePrices
  **/
  @Schema(description = "")
  public List<BigDecimal> getAvailablePrices() {
    return availablePrices;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(this.id, item.id) &&
        Objects.equals(this.name, item.name) &&
        Objects.equals(this.description, item.description) &&
        Objects.equals(this.enabled, item.enabled) &&
        Objects.equals(this.category, item.category) &&
        Objects.equals(this.imageUrl, item.imageUrl) &&
        Objects.equals(this.availablePrices, item.availablePrices);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, enabled, category, imageUrl, availablePrices);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Item {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    enabled: ").append(toIndentedString(enabled)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    availablePrices: ").append(toIndentedString(availablePrices)).append("\n");
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
