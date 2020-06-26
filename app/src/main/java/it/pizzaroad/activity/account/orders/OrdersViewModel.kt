/*
 * Project: Pizza Road
 * File: OrdersViewModel.kt
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

package it.pizzaroad.activity.account.orders

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.pizzaroad.extensions.ioJob
import it.pizzaroad.openapi.models.OrderSearchResult
import it.pizzaroad.rest.manager.impl.OrdersManager
import javax.inject.Inject

/**
 * @author fattazzo
 *         <p/>
 *         date: 17/06/20
 */
class OrdersViewModel @Inject constructor(private val ordersManager: OrdersManager): ViewModel() {

    val loadingData = ObservableBoolean(false)

    val orders = MutableLiveData<List<OrderSearchResult>>(listOf())

    fun searchOrders() {

        loadingData.set(true)
        ioJob {
            try {
                val result = ordersManager.search()
                orders.postValue(result.value ?: listOf())
            } finally {
                loadingData.set(false)
            }
        }
    }
}