/*
 * Project: Pizza Road
 * File: SessionManager.kt
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
import it.pizzaroad.openapi.api.SessionApi
import it.pizzaroad.openapi.api.SettingsApi
import it.pizzaroad.openapi.models.Session
import it.pizzaroad.openapi.models.UserLogin
import it.pizzaroad.openapi.models.UserSocialLogin
import it.pizzaroad.rest.RetrofitBuilder
import it.pizzaroad.rest.manager.AbstractRestManager
import it.pizzaroad.rest.manager.Result

/**
 * @author fattazzo
 *         <p/>
 *         date: 03/05/20
 */
class SessionManager(context: Context): AbstractRestManager(context) {

    private val sessionApi = RetrofitBuilder.client.create(SessionApi::class.java)
    private val settingsApi = RetrofitBuilder.getSecureClient(context).create(SettingsApi::class.java)

    fun login(userLogin: UserLogin): Result<Session> {

        val response = sessionApi.login(userLogin)

        val result = processResponse(response)
        result.value?.let {
            AppSessionManager.setSessionData(context,it)
            loadSettings()
        }

        return result
    }

    fun loginSocial(userSocialLogin: UserSocialLogin): Result<Session> {

        val response = sessionApi.loginSocial(userSocialLogin)

        val result = processResponse(response)
        result.value?.let {
            AppSessionManager.setSessionData(context,it)
            loadSettings()
        }

        return result
    }

    private fun loadSettings() {
        val response = settingsApi.settings
        processResponse(response).value?.let { AppSessionManager.setSettings(context,it) }
    }
}