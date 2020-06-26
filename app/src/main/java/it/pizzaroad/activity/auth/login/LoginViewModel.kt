/*
 * Project: Pizza Road
 * File: LoginViewModel.kt
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

package it.pizzaroad.activity.auth.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.pizzaroad.extensions.ioJob
import it.pizzaroad.extensions.uiJob
import it.pizzaroad.openapi.models.Session
import it.pizzaroad.openapi.models.SocialTypeEnum
import it.pizzaroad.openapi.models.UserLogin
import it.pizzaroad.openapi.models.UserSocialLogin
import it.pizzaroad.rest.manager.Result
import it.pizzaroad.rest.manager.impl.SessionManager
import java.util.*
import javax.inject.Inject

/**
 * @author fattazzo
 *         <p/>
 *         date: 01/05/20
 */
class LoginViewModel @Inject constructor(
    private val sessionManager: SessionManager
): ViewModel() {

    val username = MutableLiveData<String>().apply { postValue("") }
    val password = MutableLiveData<String>().apply { postValue("") }

    val dataValid = MediatorLiveData<Boolean>().apply {
        value = false
        addSource(username) { setValue(checkDataValid()) }
        addSource(password) { setValue(checkDataValid()) }
    }.also { it.observeForever { /* empty */ } }

    private fun checkDataValid(): Boolean {
        val usernameValid = UserValidationRules.REQUIRED.isValid(username.value)
        val passwordValid = UserValidationRules.REQUIRED.isValid(password.value)
        return usernameValid && passwordValid
    }

    fun login(onSuccessAction: () -> Unit, onFailureAction: (Result<Session>) -> Unit) {
        if(dataValid.value != true) return

        ioJob {
            val userLogin = UserLogin()
            userLogin.username = username.value!!
            userLogin.password = password.value!!
            userLogin.locale = Locale.getDefault().language

            val result = sessionManager.login(userLogin)

            uiJob {
                if(result.value != null) {
                    onSuccessAction.invoke()
                } else {
                    onFailureAction.invoke(result)
                }
            }
        }
    }

    fun loginSocial(token: String, id: String, type: SocialTypeEnum, onSuccessAction: () -> Unit, onFailureAction: (Result<Session>) -> Unit) {
        ioJob {
            val userSocialLogin = UserSocialLogin().token(token).userId(id).socialType(type).locale(Locale.getDefault().language)

            val result = sessionManager.loginSocial(userSocialLogin)

            uiJob {
                if(result.value != null) {
                    onSuccessAction.invoke()
                } else {
                    onFailureAction.invoke(result)
                }
            }
        }
    }
}