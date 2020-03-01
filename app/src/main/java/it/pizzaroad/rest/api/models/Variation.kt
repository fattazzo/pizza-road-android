/*
 * Project: Pizza Road
 * File: Variation.kt
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

package it.pizzaroad.rest.api.models


import com.google.gson.annotations.SerializedName

data class Variation(
    @SerializedName("attributes")
    val attributes: List<AttributeX>,
    @SerializedName("backordered")
    val backordered: Boolean,
    @SerializedName("backorders")
    val backorders: String,
    @SerializedName("backorders_allowed")
    val backordersAllowed: Boolean,
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("date_modified")
    val dateModified: String,
    @SerializedName("date_on_sale_from")
    val dateOnSaleFrom: String,
    @SerializedName("date_on_sale_to")
    val dateOnSaleTo: String,
    @SerializedName("dimensions")
    val dimensions: DimensionsX,
    @SerializedName("download_expiry")
    val downloadExpiry: Int,
    @SerializedName("download_limit")
    val downloadLimit: Int,
    @SerializedName("downloadable")
    val downloadable: Boolean,
    @SerializedName("downloads")
    val downloads: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: List<ImageX>,
    @SerializedName("in_stock")
    val inStock: Boolean,
    @SerializedName("manage_stock")
    val manageStock: Boolean,
    @SerializedName("on_sale")
    val onSale: Boolean,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("purchasable")
    val purchasable: Boolean,
    @SerializedName("regular_price")
    val regularPrice: String,
    @SerializedName("sale_price")
    val salePrice: String,
    @SerializedName("shipping_class")
    val shippingClass: String,
    @SerializedName("shipping_class_id")
    val shippingClassId: Int,
    @SerializedName("sku")
    val sku: String,
    @SerializedName("stock_quantity")
    val stockQuantity: Any,
    @SerializedName("tax_class")
    val taxClass: String,
    @SerializedName("tax_status")
    val taxStatus: String,
    @SerializedName("virtual")
    val virtual: Boolean,
    @SerializedName("visible")
    val visible: Boolean,
    @SerializedName("weight")
    val weight: String
)