/*
 * Project: Pizza Road
 * File: AppSessionManager.kt
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

package it.pizzaroad.app.session

import android.content.Context
import com.beust.klaxon.Klaxon
import com.google.gson.Gson
import it.pizzaroad.openapi.models.Session
import it.pizzaroad.openapi.models.Settings
import it.pizzaroad.openapi.models.User

/**
 * @author fattazzo
 *         <p/>
 *         date: 01/05/20
 */
object AppSessionManager {

    private val sessionData: AppSessionData = AppSessionData()

    private const val AUTH_PREF_FILE = "auth_prefs"

    private const val KEY_ACCESS_TOKEN = "accessToken"
    private const val KEY_REFRESH_TOKEN = "refreshToken"
    private const val KEY_USER_TOKEN = "user"
    private const val KEY_SETTINGS = "settings"

    fun getAccessToken(context: Context): String? {
        if (sessionData.accessToken == null) {
            sessionData.accessToken =
                context.getSharedPreferences(AUTH_PREF_FILE, Context.MODE_PRIVATE)
                    .getString(KEY_ACCESS_TOKEN, null)
        }
        return sessionData.accessToken
    }

    fun setSessionData(context: Context, session: Session?) {
        // access token
        context.getSharedPreferences(AUTH_PREF_FILE, Context.MODE_PRIVATE).edit().putString(
            KEY_ACCESS_TOKEN, session?.accessToken
        ).apply()
        sessionData.accessToken = session?.accessToken

        // refresh token
        context.getSharedPreferences(AUTH_PREF_FILE, Context.MODE_PRIVATE).edit().putString(
            KEY_REFRESH_TOKEN, session?.refreshToken
        ).apply()
        sessionData.refreshToken = session?.refreshToken

        // user
        context.getSharedPreferences(AUTH_PREF_FILE, Context.MODE_PRIVATE).edit().putString(
            KEY_USER_TOKEN, if(session?.userInfo != null) Gson().toJson(session.userInfo) else null
        ).apply()
        sessionData.user = session?.userInfo
    }

    fun getRefreshToken(context: Context): String? {
        if (sessionData.refreshToken == null) {
            sessionData.refreshToken =
                context.getSharedPreferences(AUTH_PREF_FILE, Context.MODE_PRIVATE)
                    .getString(KEY_REFRESH_TOKEN, null)
        }
        return sessionData.refreshToken
    }

    fun getUser(context: Context): User? {
        if (sessionData.user == null) {
            val userString = context.getSharedPreferences(AUTH_PREF_FILE, Context.MODE_PRIVATE).getString(KEY_USER_TOKEN, null)
            userString?.let { sessionData.user = Gson().fromJson(userString, User::class.java) }
        }
        return sessionData.user
    }

    fun setSettings(context: Context, settings: Settings) {

        val settingsJson = Klaxon().toJsonString(settings)

        context.getSharedPreferences(AUTH_PREF_FILE, Context.MODE_PRIVATE).edit()
            .putString(KEY_SETTINGS, settingsJson)
            .apply()
        sessionData.settings = settings
    }

    fun getSettings(context: Context): Settings {
        return if (sessionData.settings != null) {
            sessionData.settings!!
        } else {
            val lastSettingsJson =
                context.getSharedPreferences(AUTH_PREF_FILE, Context.MODE_PRIVATE).getString(
                    KEY_SETTINGS, ""
                )

            sessionData.settings = try {
                Klaxon().parse<Settings>(lastSettingsJson!!)
            } catch (e: Exception) {
                null
            }
            sessionData.settings ?: Settings().apply { currencyDecimals = 2; currencySymbol = "€" }
        }
    }
}

private class AppSessionData {
    var accessToken: String? = null
    var refreshToken: String? = null
    var settings: Settings? = null
    var user: User? = null
}