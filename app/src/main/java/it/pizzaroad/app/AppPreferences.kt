/*
 * Project: Pizza Road
 * File: AppPreferences.kt
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

package it.pizzaroad.app

import android.annotation.SuppressLint
import android.content.Context
import androidx.preference.PreferenceManager
import it.pizzaroad.R
import java.util.*

/**
 * @author fattazzo
 *         <p/>
 *         date: 03/05/20
 */
object AppPreferences {

    fun getLocale(context: Context): String {
        var locale = PreferenceManager.getDefaultSharedPreferences(context)
            .getString(context.resources.getString(R.string.preference_language_key), null)

        if (locale == null) {
            val defaultLang = Locale.getDefault().language;

            val langCodes = context.resources.getStringArray(R.array.language_code)
            langCodes.forEach {
                if (defaultLang.contains(it)) {
                    locale = it
                }
            }
        }
        return locale ?: "en"
    }

    @SuppressLint("ApplySharedPref")
    fun setLocale(context: Context, locale: String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putString(context.resources.getString(R.string.preference_language_key), locale)
            .commit()
    }
}