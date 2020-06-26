/*
 * Project: Pizza Road
 * File: OrderSearchResult
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

import org.threeten.bp.OffsetDateTime;

import java.math.BigDecimal;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * OrderSearchResult
 */


public class OrderSearchResult {
  @SerializedName("id")
  private Integer id = null;

  @SerializedName("state")
  private OrderState state = null;

  @SerializedName("shippingMethod")
  private String shippingMethod = null;

  @SerializedName("transactionId")
  private String transactionId = null;

  @SerializedName("customerNote")
  private String customerNote = null;

  @SerializedName("backofficeNote")
  private String backofficeNote = null;

  @SerializedName("dateRequest")
  private OffsetDateTime dateRequest = null;

  @SerializedName("dateCreation")
  private OffsetDateTime dateCreation = null;

  @SerializedName("dateRequestConfirmed")
  private OffsetDateTime dateRequestConfirmed = null;

  @SerializedName("total")
  private BigDecimal total = null;

  @SerializedName("shippingType")
  private ShippingType shippingType = null;

  @SerializedName("customerUserName")
  private String customerUserName = null;

  @SerializedName("deliveryAddressNumber")
  private String deliveryAddressNumber = null;

  @SerializedName("deliveryAddressStreet")
  private String deliveryAddressStreet = null;

  @SerializedName("deliveryAddressPlace")
  private String deliveryAddressPlace = null;

  @SerializedName("deliveryAddressPostalCode")
  private String deliveryAddressPostalCode = null;

  public OrderSearchResult id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @Schema(required = true, description = "")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public OrderSearchResult state(OrderState state) {
    this.state = state;
    return this;
  }

   /**
   * Get state
   * @return state
  **/
  @Schema(required = true, description = "")
  public OrderState getState() {
    return state;
  }

  public void setState(OrderState state) {
    this.state = state;
  }

  public OrderSearchResult shippingMethod(String shippingMethod) {
    this.shippingMethod = shippingMethod;
    return this;
  }

   /**
   * Get shippingMethod
   * @return shippingMethod
  **/
  @Schema(required = true, description = "")
  public String getShippingMethod() {
    return shippingMethod;
  }

  public void setShippingMethod(String shippingMethod) {
    this.shippingMethod = shippingMethod;
  }

  public OrderSearchResult transactionId(String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

   /**
   * Get transactionId
   * @return transactionId
  **/
  @Schema(description = "")
  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public OrderSearchResult customerNote(String customerNote) {
    this.customerNote = customerNote;
    return this;
  }

   /**
   * Get customerNote
   * @return customerNote
  **/
  @Schema(description = "")
  public String getCustomerNote() {
    return customerNote;
  }

  public void setCustomerNote(String customerNote) {
    this.customerNote = customerNote;
  }

  public OrderSearchResult backofficeNote(String backofficeNote) {
    this.backofficeNote = backofficeNote;
    return this;
  }

   /**
   * Get backofficeNote
   * @return backofficeNote
  **/
  @Schema(description = "")
  public String getBackofficeNote() {
    return backofficeNote;
  }

  public void setBackofficeNote(String backofficeNote) {
    this.backofficeNote = backofficeNote;
  }

  public OrderSearchResult dateRequest(OffsetDateTime dateRequest) {
    this.dateRequest = dateRequest;
    return this;
  }

   /**
   * Get dateRequest
   * @return dateRequest
  **/
  @Schema(required = true, description = "")
  public OffsetDateTime getDateRequest() {
    return dateRequest;
  }

  public void setDateRequest(OffsetDateTime dateRequest) {
    this.dateRequest = dateRequest;
  }

  public OrderSearchResult dateCreation(OffsetDateTime dateCreation) {
    this.dateCreation = dateCreation;
    return this;
  }

   /**
   * Get dateCreation
   * @return dateCreation
  **/
  @Schema(required = true, description = "")
  public OffsetDateTime getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(OffsetDateTime dateCreation) {
    this.dateCreation = dateCreation;
  }

  public OrderSearchResult dateRequestConfirmed(OffsetDateTime dateRequestConfirmed) {
    this.dateRequestConfirmed = dateRequestConfirmed;
    return this;
  }

   /**
   * Get dateRequestConfirmed
   * @return dateRequestConfirmed
  **/
  @Schema(description = "")
  public OffsetDateTime getDateRequestConfirmed() {
    return dateRequestConfirmed;
  }

  public void setDateRequestConfirmed(OffsetDateTime dateRequestConfirmed) {
    this.dateRequestConfirmed = dateRequestConfirmed;
  }

  public OrderSearchResult total(BigDecimal total) {
    this.total = total;
    return this;
  }

   /**
   * Get total
   * @return total
  **/
  @Schema(description = "")
  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public OrderSearchResult shippingType(ShippingType shippingType) {
    this.shippingType = shippingType;
    return this;
  }

   /**
   * Get shippingType
   * @return shippingType
  **/
  @Schema(required = true, description = "")
  public ShippingType getShippingType() {
    return shippingType;
  }

  public void setShippingType(ShippingType shippingType) {
    this.shippingType = shippingType;
  }

  public OrderSearchResult customerUserName(String customerUserName) {
    this.customerUserName = customerUserName;
    return this;
  }

   /**
   * Get customerUserName
   * @return customerUserName
  **/
  @Schema(required = true, description = "")
  public String getCustomerUserName() {
    return customerUserName;
  }

  public void setCustomerUserName(String customerUserName) {
    this.customerUserName = customerUserName;
  }

  public OrderSearchResult deliveryAddressNumber(String deliveryAddressNumber) {
    this.deliveryAddressNumber = deliveryAddressNumber;
    return this;
  }

   /**
   * Get deliveryAddressNumber
   * @return deliveryAddressNumber
  **/
  @Schema(description = "")
  public String getDeliveryAddressNumber() {
    return deliveryAddressNumber;
  }

  public void setDeliveryAddressNumber(String deliveryAddressNumber) {
    this.deliveryAddressNumber = deliveryAddressNumber;
  }

  public OrderSearchResult deliveryAddressStreet(String deliveryAddressStreet) {
    this.deliveryAddressStreet = deliveryAddressStreet;
    return this;
  }

   /**
   * Get deliveryAddressStreet
   * @return deliveryAddressStreet
  **/
  @Schema(description = "")
  public String getDeliveryAddressStreet() {
    return deliveryAddressStreet;
  }

  public void setDeliveryAddressStreet(String deliveryAddressStreet) {
    this.deliveryAddressStreet = deliveryAddressStreet;
  }

  public OrderSearchResult deliveryAddressPlace(String deliveryAddressPlace) {
    this.deliveryAddressPlace = deliveryAddressPlace;
    return this;
  }

   /**
   * Get deliveryAddressPlace
   * @return deliveryAddressPlace
  **/
  @Schema(description = "")
  public String getDeliveryAddressPlace() {
    return deliveryAddressPlace;
  }

  public void setDeliveryAddressPlace(String deliveryAddressPlace) {
    this.deliveryAddressPlace = deliveryAddressPlace;
  }

  public OrderSearchResult deliveryAddressPostalCode(String deliveryAddressPostalCode) {
    this.deliveryAddressPostalCode = deliveryAddressPostalCode;
    return this;
  }

   /**
   * Get deliveryAddressPostalCode
   * @return deliveryAddressPostalCode
  **/
  @Schema(description = "")
  public String getDeliveryAddressPostalCode() {
    return deliveryAddressPostalCode;
  }

  public void setDeliveryAddressPostalCode(String deliveryAddressPostalCode) {
    this.deliveryAddressPostalCode = deliveryAddressPostalCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderSearchResult orderSearchResult = (OrderSearchResult) o;
    return Objects.equals(this.id, orderSearchResult.id) &&
        Objects.equals(this.state, orderSearchResult.state) &&
        Objects.equals(this.shippingMethod, orderSearchResult.shippingMethod) &&
        Objects.equals(this.transactionId, orderSearchResult.transactionId) &&
        Objects.equals(this.customerNote, orderSearchResult.customerNote) &&
        Objects.equals(this.backofficeNote, orderSearchResult.backofficeNote) &&
        Objects.equals(this.dateRequest, orderSearchResult.dateRequest) &&
        Objects.equals(this.dateCreation, orderSearchResult.dateCreation) &&
        Objects.equals(this.dateRequestConfirmed, orderSearchResult.dateRequestConfirmed) &&
        Objects.equals(this.total, orderSearchResult.total) &&
        Objects.equals(this.shippingType, orderSearchResult.shippingType) &&
        Objects.equals(this.customerUserName, orderSearchResult.customerUserName) &&
        Objects.equals(this.deliveryAddressNumber, orderSearchResult.deliveryAddressNumber) &&
        Objects.equals(this.deliveryAddressStreet, orderSearchResult.deliveryAddressStreet) &&
        Objects.equals(this.deliveryAddressPlace, orderSearchResult.deliveryAddressPlace) &&
        Objects.equals(this.deliveryAddressPostalCode, orderSearchResult.deliveryAddressPostalCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, state, shippingMethod, transactionId, customerNote, backofficeNote, dateRequest, dateCreation, dateRequestConfirmed, total, shippingType, customerUserName, deliveryAddressNumber, deliveryAddressStreet, deliveryAddressPlace, deliveryAddressPostalCode);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderSearchResult {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    shippingMethod: ").append(toIndentedString(shippingMethod)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    customerNote: ").append(toIndentedString(customerNote)).append("\n");
    sb.append("    backofficeNote: ").append(toIndentedString(backofficeNote)).append("\n");
    sb.append("    dateRequest: ").append(toIndentedString(dateRequest)).append("\n");
    sb.append("    dateCreation: ").append(toIndentedString(dateCreation)).append("\n");
    sb.append("    dateRequestConfirmed: ").append(toIndentedString(dateRequestConfirmed)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    shippingType: ").append(toIndentedString(shippingType)).append("\n");
    sb.append("    customerUserName: ").append(toIndentedString(customerUserName)).append("\n");
    sb.append("    deliveryAddressNumber: ").append(toIndentedString(deliveryAddressNumber)).append("\n");
    sb.append("    deliveryAddressStreet: ").append(toIndentedString(deliveryAddressStreet)).append("\n");
    sb.append("    deliveryAddressPlace: ").append(toIndentedString(deliveryAddressPlace)).append("\n");
    sb.append("    deliveryAddressPostalCode: ").append(toIndentedString(deliveryAddressPostalCode)).append("\n");
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
