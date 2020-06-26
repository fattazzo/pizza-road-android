/*
 * Project: Pizza Road
 * File: AppCartManager.kt
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

package it.pizzaroad.app.cart

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import it.pizzaroad.openapi.models.*
import it.pizzaroad.rest.converters.OffsetDateTimeConverter
import org.threeten.bp.OffsetDateTime
import java.math.BigDecimal

/**
 * @author fattazzo
 *         <p/>
 *         date: 14/05/20
 */
object AppCartManager {

    private var order: OrderDetails? = null

    private const val CART_PREF_FILE = "cart"

    private const val KEY_CART = "current"

    private val gson : Gson by lazy {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeConverter())
        gsonBuilder.create()
    }

    fun getCurrent(context: Context): OrderDetails {
        if (order == null) {
            val orderString = context.getSharedPreferences(CART_PREF_FILE, Context.MODE_PRIVATE)
                .getString(KEY_CART, null)
            orderString?.let {
                order = try {
                    gson.fromJson(orderString, OrderDetails::class.java)
                } catch (e: Exception) {
                    null
                }
            }
        }
        return order ?: OrderDetails()
    }

    fun updateShippingMethod(context: Context, shippingMethod: ShippingMethod?) {
        val currentOrder = getCurrent(context)
        currentOrder.shippingMethod = shippingMethod
        currentOrder.transactionId = null;
        setCurrent(context, currentOrder)
    }

    fun updateShippingType(context: Context, shippingType: ShippingType?) {
        val currentOrder = getCurrent(context)
        currentOrder.shippingType = shippingType
        setCurrent(context, currentOrder)
    }

    fun updateShippingAddress(context: Context, address: Address?) {
        val currentOrder = getCurrent(context)
        currentOrder.deliveryAddress = address
        setCurrent(context, currentOrder)
    }

    fun updateDateRequest(context: Context, dateRequest: OffsetDateTime?) {
        val currentOrder = getCurrent(context)
        currentOrder.dateRequest = dateRequest
        setCurrent(context, currentOrder)
    }

    fun updateTransactionId(context: Context, id: String) {
        val currentOrder = getCurrent(context)
        currentOrder.transactionId = id
        setCurrent(context, currentOrder)
    }

    fun addPizzaItem(
        context: Context,
        itemPizza: ItemPizza,
        quantity: Int,
        total: BigDecimal,
        itemPizzaPrice: ItemPizzaPrice,
        variationDough: VariationDough,
        toppingExtras: List<ToppingExtra>?
    ) {

        val line = OrderLine().item(itemPizza).pizzaDough(variationDough).pizzaPrice(itemPizzaPrice)
            .pizzaToppingExtras(toppingExtras.orEmpty()).quantity(quantity).total(total)

        val currentOrder = getCurrent(context)
        currentOrder.addLinesItem(line)
        currentOrder.total = (currentOrder.total ?: BigDecimal.ZERO).add(total)
        setCurrent(context, currentOrder)
    }

    fun addProductItem(
        context: Context,
        itemProduct: ItemProduct,
        quantity: Int,
        total: BigDecimal,
        itemProductPrice: ItemProductPrice
    ) {

        val line = OrderLine().item(itemProduct).productPrice(itemProductPrice).quantity(quantity).total(total)

        val currentOrder = getCurrent(context)
        currentOrder.addLinesItem(line)
        currentOrder.total = (currentOrder.total ?: BigDecimal.ZERO).add(total)
        setCurrent(context, currentOrder)
    }

    private fun setCurrent(context: Context, orderDetails: OrderDetails?) {
        order = if (orderDetails == null) {
            context.getSharedPreferences(CART_PREF_FILE, Context.MODE_PRIVATE).edit().remove(KEY_CART).apply()
            null
        } else {
            val orderString = gson.toJson(orderDetails);
            context.getSharedPreferences(CART_PREF_FILE, Context.MODE_PRIVATE).edit().putString(KEY_CART, orderString)
                .apply()
            orderDetails
        }
    }
}