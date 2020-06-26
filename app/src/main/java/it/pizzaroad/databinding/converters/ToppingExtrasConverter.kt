/*
 * Project: Pizza Road
 * File: ToppingExtrasConverter.kt
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

package it.pizzaroad.databinding.converters

import android.content.Context
import it.pizzaroad.app.session.AppSessionManager
import it.pizzaroad.openapi.models.ToppingExtra
import java.text.DecimalFormat

/**
 * @author fattazzo
 *         <p/>
 *         date: 15/05/20
 */
object ToppingExtrasConverter {

    @JvmStatic
    fun toString(context: Context, toppings: List<ToppingExtra>): String {

        return toppings.joinToString(separator = "\n",transform ={
            val extra = try {
                val decimalFormat = DecimalFormat("#,##0.${"0".repeat(AppSessionManager.getSettings(context).currencyDecimals)}")

                "${decimalFormat.format(it.extra)} ${AppSessionManager.getSettings(context).currencySymbol}"
            } catch (e: Exception) {
                it.extra?.toString() ?: ""
            }
            "${it.topping.name} ( ${extra})"
        })
    }
}