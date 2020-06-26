/*
 * Project: Pizza Road
 * File: AbstractRestManager.kt
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

package it.pizzaroad.rest.manager

import android.content.Context
import it.pizzaroad.extensions.TAG
import it.pizzaroad.rest.error.ErrorHandler
import it.pizzaroad.rest.extensions.log
import retrofit2.Call

/**
 * @author fattazzo
 *         <p/>
 *         date: 28/02/20
 */
abstract class AbstractRestManager(protected val context: Context) {

    protected fun <T> processResponse(
        call: Call<T>,
        showErrorMessage: Boolean = true
    ): Result<T> {
        return try {
            val response = call.execute()
            response.log(TAG)
            if (response.isSuccessful) {
                Result(response.body(), null, null)
            } else {
                val errorData = ErrorHandler.parse(context,response);
                if (showErrorMessage)
                    ErrorHandler.showToastError(context,errorData)
                Result(null, response.code(), errorData)
            }
        } catch (e: Exception) {
            if (showErrorMessage)
                ErrorHandler.showToastError(context)
            Result(null, 400, null)
        }
    }
}