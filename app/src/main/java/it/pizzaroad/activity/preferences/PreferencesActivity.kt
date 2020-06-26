/*
 * Project: Pizza Road
 * File: PreferencesActivity.kt
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

package it.pizzaroad.activity.preferences

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import it.pizzaroad.R
import it.pizzaroad.activity.preferences.handler.PreferenceHandler

/**
 * @author fattazzo
 *         <p/>
 *         date: 03/05/20
 */
class PreferencesActivity: AppCompatActivity(R.layout.activity_settings) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, PreferencesFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class PreferencesFragment : PreferenceFragmentCompat() {

        private val prefsList = mutableListOf<PreferenceHandler>()

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            initPreferencesHandler()

            // Update all summary
            context?.let { prefsList.forEach { pref -> pref.updatesummary(it) } }
        }

        override fun onPreferenceTreeClick(preference: Preference?): Boolean {
            context?.let {
                prefsList.firstOrNull { handler -> handler.getKey() == preference?.key }
                    ?.handle(it)
            }
            return super.onPreferenceTreeClick(preference)
        }

        private fun initPreferencesHandler() {
            //findPref(R.string.preference_language_key)?.let { prefsList.add(PreferenceLanguageHandler(it)) }
        }

        private fun findPref(@StringRes key: Int): Preference? =
            findPreference(resources.getString(key))
    }
}