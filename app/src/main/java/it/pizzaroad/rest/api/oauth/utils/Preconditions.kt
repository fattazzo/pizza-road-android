/*
 * Project: Pizza Road
 * File: Preconditions.kt
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
package it.pizzaroad.rest.api.oauth.utils

import it.pizzaroad.rest.api.oauth.constants.OAuthConstants
import java.util.regex.Pattern

/**
 * Utils for checking preconditions and invariants
 */
object Preconditions {
    private const val DEFAULT_MESSAGE = "Received an invalid parameter"
    // scheme = alpha *( alpha | digit | "+" | "-" | "." )
    private val URL_PATTERN =
        Pattern.compile("^[a-zA-Z][a-zA-Z0-9+.-]*://\\S+")

    /**
     * Checks that an object is not null.
     *
     * @param objectParam   any object
     * @param errorMsg error message
     * @throws IllegalArgumentException if the object is null
     */
    fun checkNotNull(objectParam: Any?, errorMsg: String?) {
        check(objectParam != null, errorMsg)
    }

    /**
     * Checks that a string is not null or empty
     *
     * @param string   any string
     * @param errorMsg error message
     * @throws IllegalArgumentException if the string is null or empty
     */
    @JvmStatic
    fun checkEmptyString(string: String?, errorMsg: String?) {
        check(
            string != null && string.trim { it <= ' ' } != "",
            errorMsg
        )
    }

    /**
     * Checks that a URL is valid
     *
     * @param url      any string
     * @param errorMsg error message
     */
    fun checkValidUrl(url: String?, errorMsg: String?) {
        checkEmptyString(url, errorMsg)
        check(
            isUrl(
                url
            ), errorMsg
        )
    }

    /**
     * Checks that a URL is a valid OAuth callback
     *
     * @param url      any string
     * @param errorMsg error message
     */
    fun checkValidOAuthCallback(url: String, errorMsg: String?) {
        checkEmptyString(url, errorMsg)
        if (url.toLowerCase().compareTo(OAuthConstants.OUT_OF_BAND, ignoreCase = true) != 0) {
            check(
                isUrl(
                    url
                ), errorMsg
            )
        }
    }

    private fun isUrl(url: String?): Boolean {
        return URL_PATTERN.matcher(url.orEmpty()).matches()
    }

    private fun check(requirements: Boolean, error: String?) {
        val message =
            if (error == null || error.trim { it <= ' ' }.isEmpty()) DEFAULT_MESSAGE else error
        require(requirements) { message }
    }
}