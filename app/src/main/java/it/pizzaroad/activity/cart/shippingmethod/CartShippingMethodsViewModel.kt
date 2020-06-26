/*
 * Project: Pizza Road
 * File: CartShippingMethodsViewModel.kt
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

package it.pizzaroad.activity.cart.shippingmethod

import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.pizzaroad.app.cart.AppCartManager
import it.pizzaroad.app.session.AppSessionManager
import it.pizzaroad.extensions.ioJob
import it.pizzaroad.extensions.uiJob
import it.pizzaroad.openapi.models.Address
import it.pizzaroad.openapi.models.ShippingMethod
import it.pizzaroad.openapi.models.ShippingType
import it.pizzaroad.openapi.models.UserDetails
import it.pizzaroad.rest.manager.impl.ShippingMethodsManager
import it.pizzaroad.rest.manager.impl.UsersManager
import javax.inject.Inject

/**
 * @author fattazzo
 *         <p/>
 *         date: 15/05/20
 */
class CartShippingMethodsViewModel @Inject constructor(
    private val shippingMethodsManager: ShippingMethodsManager,
    private val usersManager: UsersManager
) : ViewModel() {

    var shippingMethod = MutableLiveData<ShippingMethod>(null)
    var availableShippingMethods = MutableLiveData<List<ShippingMethod>>(listOf())

    var deliveryAddress = MutableLiveData<Address>(null)
    var availableDeliveryAddresses = MutableLiveData<List<Address>>(listOf())

    var shippingType = MutableLiveData<ShippingType>(null)

    val loading = ObservableBoolean(false)

    fun initData(context: Context) {
        if(availableShippingMethods.value.isNullOrEmpty()) {

            ioJob {
                loading.set(true)

                var result: List<ShippingMethod>? = null
                try {
                     result = shippingMethodsManager.getShippingMethods().value
                } finally {
                    uiJob {
                        availableShippingMethods.postValue(result.orEmpty())
                    }
                    loading.set(true)
                }
            }
        }

        if(availableDeliveryAddresses.value.isNullOrEmpty()) {
            val user = AppSessionManager.getUser(context) ?: return

            ioJob {
                loading.set(true)

                var result: UserDetails? = null
                try {
                    result = usersManager.getUser(user.username).value
                } finally {
                    uiJob {
                        result?.let {
                            availableDeliveryAddresses.postValue(it.deliveryAddresses.map { da -> da.address })
                        }
                    }
                    loading.set(false)
                }
            }
        }
    }

    fun updateShippingMethod(context: Context, shippingMethod: ShippingMethod) {
        this.shippingMethod.postValue(shippingMethod)
        AppCartManager.updateShippingMethod(context,shippingMethod)
        updateShippingType(context,null)
    }

    fun updateShippingType(context: Context, shippingType: ShippingType?) {
        this.shippingType.postValue(shippingType)
        AppCartManager.updateShippingType(context,shippingType)
        updateAddress(context,null)
    }

    fun updateAddress(context: Context, address: Address?) {
        this.deliveryAddress.postValue(address)
        AppCartManager.updateShippingAddress(context,address)
    }
}