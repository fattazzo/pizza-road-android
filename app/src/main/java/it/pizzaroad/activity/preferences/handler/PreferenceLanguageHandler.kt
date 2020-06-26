/*
 * Project: Pizza Road
 * File: PreferenceLanguageHandler.kt
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

package it.pizzaroad.activity.preferences.handler

import android.content.Context
import androidx.preference.Preference
import it.pizzaroad.R
import it.pizzaroad.app.AppPreferences

/**
 * @author fattazzo
 *         <p/>
 *         date: 03/05/20
 */
class PreferenceLanguageHandler(preference: Preference): PreferenceHandler(preference) {

    override fun handle(context: Context) {
        updatesummary(context)
    }

    override fun updatesummary(context: Context) {
        val locale = AppPreferences.getLocale(context)

        val langCode = context.resources.getStringArray(R.array.language_code).indexOf(locale);

        preference.summary = context.resources.getStringArray(R.array.language_entry)[langCode]
    }
}