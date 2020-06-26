/*
 * Project: Pizza Road
 * File: ErrorHandler.kt
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

package it.pizzaroad.rest.error

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.google.gson.Gson
import it.pizzaroad.R
import it.pizzaroad.openapi.models.ErrorData
import it.pizzaroad.openapi.models.ErrorResponse
import it.pizzaroad.rest.extensions.toHtmlString
import retrofit2.Response

/**
 * @author fattazzo
 *         <p/>
 *         date: 03/05/20
 */
object ErrorHandler {

    fun parse(context: Context, response: Response<*>): ErrorData {
        return try {
            Gson().fromJson(response.errorBody()?.charStream(), ErrorResponse::class.java).error
        } catch (e: Exception) {
            getDefaultError(context)
        }
    }

    fun showError(context: Context, error: ErrorData? = null) {
        if(error?.constraintErrors?.isNotEmpty() == true) {
            showDialogError(context, error)
        } else {
            showToastError(context, error)
        }
    }

    private fun getDefaultError(context: Context): ErrorData {
        return ErrorData()
            .userTitle(context.getString(R.string.error_default_title))
            .userMessage(context.getString(R.string.error_default_message))
    }

    fun showToastError(context: Context, error: ErrorData? = null) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, (error ?: getDefaultError(context)).toHtmlString(context), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDialogError(context: Context, error: ErrorData? = null) {
        Handler(Looper.getMainLooper()).post {
            val errorData = error ?: getDefaultError(context)

            MaterialDialog(context).show {
                title(text = errorData.userTitle)
                message(text = errorData.toHtmlString(context,false))
            }
        }
    }
}