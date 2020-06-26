/*
 * Project: Pizza Road
 * File: AccountViewModel.kt
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

package it.pizzaroad.activity.account

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.pizzaroad.extensions.ioJob
import it.pizzaroad.extensions.uiJob
import it.pizzaroad.openapi.models.DeliveryAddress
import it.pizzaroad.openapi.models.UserDetails
import it.pizzaroad.rest.manager.impl.UsersManager
import javax.inject.Inject

/**
 * @author fattazzo
 *         <p/>
 *         date: 15/05/20
 */
class AccountViewModel @Inject constructor(private val usersManager: UsersManager) : ViewModel() {

    var user = MutableLiveData<UserDetails>(null)

    val loading = ObservableBoolean(false)

    fun init(username: String?) {

        if (username != null && user.value != null) {
            return
        }

        loading.set(true)
        try {
            ioJob {
                val result = usersManager.getUser(username!!).value
                uiJob { user.postValue(result) }
            }
        } finally {
            loading.set(false)
        }
    }

    fun saveUser() {
        loading.set(true)
        try {
            ioJob {
                val result = usersManager.updateUser(user.value!!).value
                uiJob { user.postValue(result) }
            }
        } finally {
            loading.set(false)
        }
    }

    fun addDeliveryAddress(deliveryAddress: DeliveryAddress) {
        val currentUser = user.value!!
        currentUser.addDeliveryAddressesItem(deliveryAddress)
        user.postValue(currentUser)
    }
}