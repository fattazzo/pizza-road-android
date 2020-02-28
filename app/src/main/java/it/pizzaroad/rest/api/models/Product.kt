/*
 * Project: Pizza Road
 * File: Product.kt
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

data class Product(
    @SerializedName("attributes")
    val attributes: List<Any>,
    @SerializedName("average_rating")
    val averageRating: String,
    @SerializedName("backordered")
    val backordered: Boolean,
    @SerializedName("backorders")
    val backorders: String,
    @SerializedName("backorders_allowed")
    val backordersAllowed: Boolean,
    @SerializedName("button_text")
    val buttonText: String,
    @SerializedName("catalog_visibility")
    val catalogVisibility: String,
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("cross_sell_ids")
    val crossSellIds: List<Any>,
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("date_modified")
    val dateModified: String,
    @SerializedName("date_on_sale_from")
    val dateOnSaleFrom: String,
    @SerializedName("date_on_sale_to")
    val dateOnSaleTo: String,
    @SerializedName("default_attributes")
    val defaultAttributes: List<Any>,
    @SerializedName("description")
    val description: String,
    @SerializedName("dimensions")
    val dimensions: Dimensions,
    @SerializedName("download_expiry")
    val downloadExpiry: Int,
    @SerializedName("download_limit")
    val downloadLimit: Int,
    @SerializedName("download_type")
    val downloadType: String,
    @SerializedName("downloadable")
    val downloadable: Boolean,
    @SerializedName("downloads")
    val downloads: List<Any>,
    @SerializedName("external_url")
    val externalUrl: String,
    @SerializedName("featured")
    val featured: Boolean,
    @SerializedName("grouped_products")
    val groupedProducts: List<Any>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("in_stock")
    val inStock: Boolean,
    @SerializedName("lang")
    val lang: String,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("manage_stock")
    val manageStock: Boolean,
    @SerializedName("menu_order")
    val menuOrder: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("on_sale")
    val onSale: Boolean,
    @SerializedName("parent_id")
    val parentId: Int,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("price_html")
    val priceHtml: String,
    @SerializedName("purchasable")
    val purchasable: Boolean,
    @SerializedName("purchase_note")
    val purchaseNote: String,
    @SerializedName("rating_count")
    val ratingCount: Int,
    @SerializedName("regular_price")
    val regularPrice: String,
    @SerializedName("related_ids")
    val relatedIds: List<Int>,
    @SerializedName("reviews_allowed")
    val reviewsAllowed: Boolean,
    @SerializedName("sale_price")
    val salePrice: String,
    @SerializedName("shipping_class")
    val shippingClass: String,
    @SerializedName("shipping_class_id")
    val shippingClassId: Int,
    @SerializedName("shipping_required")
    val shippingRequired: Boolean,
    @SerializedName("shipping_taxable")
    val shippingTaxable: Boolean,
    @SerializedName("short_description")
    val shortDescription: String,
    @SerializedName("sku")
    val sku: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("sold_individually")
    val soldIndividually: Boolean,
    @SerializedName("status")
    val status: String,
    @SerializedName("stock_quantity")
    val stockQuantity: Any,
    @SerializedName("tags")
    val tags: List<Any>,
    @SerializedName("tax_class")
    val taxClass: String,
    @SerializedName("tax_status")
    val taxStatus: String,
    @SerializedName("total_sales")
    val totalSales: Int,
    @SerializedName("translations")
    val translations: List<Any>,
    @SerializedName("type")
    val type: String,
    @SerializedName("upsell_ids")
    val upsellIds: List<Any>,
    @SerializedName("variations")
    val variations: List<Any>,
    @SerializedName("virtual")
    val virtual: Boolean,
    @SerializedName("weight")
    val weight: String
)