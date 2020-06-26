/*
 * Project: Pizza Road
 * File: CategoriesManager.kt
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
import it.pizzaroad.openapi.api.CategoriesApi
import it.pizzaroad.openapi.models.Category
import it.pizzaroad.rest.RetrofitBuilder
import it.pizzaroad.rest.manager.AbstractRestManager
import it.pizzaroad.rest.manager.Result

/**
 * @author fattazzo
 *         <p/>
 *         date: 03/05/20
 */
class CategoriesManager(context: Context) : AbstractRestManager(context) {

    private val categoriesApi =
        RetrofitBuilder.getSecureClient(context).create(CategoriesApi::class.java)

    fun getCategories(): Result<List<Category>> {

        val response = categoriesApi.getCategories(false)

        return processResponse(response)
    }
}