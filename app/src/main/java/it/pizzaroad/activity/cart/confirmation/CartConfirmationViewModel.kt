/*
 * Project: Pizza Road
 * File: CartConfirmationViewModel.kt
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

package it.pizzaroad.activity.cart.confirmation

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.pizzaroad.app.cart.AppCartManager
import it.pizzaroad.app.session.AppSessionManager
import it.pizzaroad.extensions.ioJob
import it.pizzaroad.extensions.uiJob
import it.pizzaroad.openapi.models.ErrorData
import it.pizzaroad.rest.manager.impl.OrdersManager
import org.threeten.bp.Clock
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

/**
 * @author fattazzo
 *         <p/>
 *         date: 18/05/20
 */
class CartConfirmationViewModel @Inject constructor(
    private val ordersManager: OrdersManager,
    private val context: Context
) : ViewModel() {

    val minOrderRequestMinutes = MutableLiveData(0)
    val customDateRequest = MutableLiveData(false)

    val dateRequest = MediatorLiveData<OffsetDateTime>().apply {
        postValue(null)
        addSource(customDateRequest) { calculateDateRequest() }
        addSource(minOrderRequestMinutes) { calculateDateRequest() }
    }.also { it.observeForever { /* empty */ } }

    val sendingOrder = ObservableBoolean(false)

    fun initData(context: Context) {
        minOrderRequestMinutes.postValue(AppSessionManager.getSettings(context).minOrderRequestMinutes)

        calculateDateRequest()
    }

    private fun calculateDateRequest() {
        val instantRequest = Instant.now().plusSeconds((minOrderRequestMinutes.value!! * 60).toLong())

        // dateRequest can be null on mediator initializzation
        if (dateRequest != null && customDateRequest.value == false) {
            val newValue = OffsetDateTime.ofInstant(instantRequest,Clock.systemDefaultZone().zone)
            dateRequest.postValue(newValue)
            AppCartManager.updateDateRequest(context,null)
        }
    }

    fun updateRequestTime(hourOfDay: Int, minute: Int) {
        if(customDateRequest.value == true) {
            val currentDate = OffsetDateTime.now()
            val newRequestDate = OffsetDateTime.of(currentDate.year,currentDate.monthValue,currentDate.dayOfMonth,hourOfDay,minute,0,0,currentDate.offset)
            dateRequest.postValue(newRequestDate)
            AppCartManager.updateDateRequest(context,newRequestDate)
        }
    }

    fun createOrder(context: Context, onSucces: () -> Unit, onFailure: (ErrorData?) -> Unit) {

        sendingOrder.set(true)
        ioJob {
            try {
                val result = ordersManager.createOrder(AppCartManager.getCurrent(context))

                uiJob {
                    if(result.error == null) {
                        onSucces.invoke()
                    } else {
                        onFailure.invoke(result.errorData)
                    }
                }
            } finally {
                sendingOrder.set(false)
            }
        }
    }
}