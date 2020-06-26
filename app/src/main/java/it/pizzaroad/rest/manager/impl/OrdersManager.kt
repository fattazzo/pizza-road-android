/*
 * Project: Pizza Road
 * File: OrdersManager.kt
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

package it.pizzaroad.rest.manager.impl

import android.content.Context
import it.pizzaroad.app.session.AppSessionManager
import it.pizzaroad.openapi.api.OrdersApi
import it.pizzaroad.openapi.models.OrderDetails
import it.pizzaroad.openapi.models.OrderSearchParameters
import it.pizzaroad.openapi.models.OrderSearchResult
import it.pizzaroad.rest.RetrofitBuilder
import it.pizzaroad.rest.manager.AbstractRestManager
import it.pizzaroad.rest.manager.Result
import it.pizzaroad.rest.modelconverters.OrderRequestConverter
import org.threeten.bp.Clock
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime

/**
 * @author fattazzo
 *         <p/>
 *         date: 15/05/20
 */
class OrdersManager(context: Context) : AbstractRestManager(context) {

    private val ordersApi = RetrofitBuilder.getSecureClient(context).create(OrdersApi::class.java)

    fun createOrder(orderDetails: OrderDetails): Result<Void> {
        val orderRequest = OrderRequestConverter.fromOrderDetails(orderDetails)
        orderRequest.dateCreation = OffsetDateTime.now()
        if(orderRequest.dateRequest == null) {
            val instantRequest = Instant.now().plusSeconds((AppSessionManager.getSettings(context).minOrderRequestMinutes!! * 60).toLong())
            val newValue = OffsetDateTime.ofInstant(instantRequest, Clock.systemDefaultZone().zone)
            orderRequest.dateRequest = newValue
        }
        val response = ordersApi.createOrder(orderRequest)
        return processResponse(response,false)
    }

    fun search(): Result<List<OrderSearchResult>> {
        val response = ordersApi.searchOrders(OrderSearchParameters())
        return processResponse(response)
    }
}