/*
 * Project: Pizza Road
 * File: UsersManager.kt
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
import it.pizzaroad.openapi.api.UsersApi
import it.pizzaroad.openapi.models.UserDetails
import it.pizzaroad.rest.RetrofitBuilder
import it.pizzaroad.rest.manager.AbstractRestManager
import it.pizzaroad.rest.manager.Result

/**
 * @author fattazzo
 *         <p/>
 *         date: 15/05/20
 */
class UsersManager(context: Context) : AbstractRestManager(context) {

    private val usersApi = RetrofitBuilder.getSecureClient(context).create(UsersApi::class.java)

    fun getUser(username: String): Result<UserDetails> {
        val response = usersApi.getUser(username)
        return processResponse(response)
    }

    fun updateUser(user: UserDetails): Result<UserDetails> {
        val response = usersApi.updateUser(user,AppSessionManager.getUser(context)?.username)
        return processResponse(response)
    }
}