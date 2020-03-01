/*
 * Project: Pizza Road
 * File: ProductCategoriesRestService.kt
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

package it.pizzaroad.rest.api.services.products

import it.pizzaroad.rest.api.models.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author fattazzo
 *         <p/>
 *         date: 29/02/20
 */
interface ProductCategoriesRestService {

    /**
     * List all product categories
     *
     * This API lets you retrieve all product categories.
     *
     * @param itemsPerPage Maximum number of items to be returned in result set. Default is 10.
     */
    @GET("products/categories")
    fun list(@Query("per_page") itemsPerPage: Int = 10): Call<List<Category>>
}