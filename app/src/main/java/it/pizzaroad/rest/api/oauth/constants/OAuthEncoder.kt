/*
 * Project: Pizza Road
 * File: OAuthEncoder.kt
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
package it.pizzaroad.rest.api.oauth.constants

import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.*
import java.util.regex.Pattern

/**
 * @author fattazzo
 *
 *
 * date: 28/02/20
 */
object OAuthEncoder {
    private var ENCODING_RULES: Map<String, String>? = null
    private const val CHARSET = "UTF-8"
    @JvmStatic
    fun encode(plain: String?): String { //        Preconditions.checkNotNull(plain, "Cannot encode null object");
        var encoded = ""
        encoded = try {
            URLEncoder.encode(plain, CHARSET)
        } catch (uee: UnsupportedEncodingException) {
            throw OAuthException(
                "Charset not found while encoding string: $CHARSET",
                uee
            )
        }
        for ((key, value) in ENCODING_RULES!!) {
            encoded = applyRule(encoded, key, value)
        }
        return encoded
    }

    private fun applyRule(
        encoded: String,
        toReplace: String,
        replacement: String
    ): String {
        return encoded.replace(Pattern.quote(toReplace).toRegex(), replacement)
    }

    @JvmStatic
    fun decode(encoded: String?): String { //        Preconditions.checkNotNull(encoded, "Cannot decode null object");
        return try {
            URLDecoder.decode(encoded, CHARSET)
        } catch (uee: UnsupportedEncodingException) {
            throw OAuthException(
                "Charset not found while decoding string: $CHARSET",
                uee
            )
        }
    }

    init {
        val rules: MutableMap<String, String> = mutableMapOf()
        rules["*"] = "%2A"
        rules["+"] = "%20"
        rules["%7E"] = "~"

        ENCODING_RULES = Collections.unmodifiableMap(rules)
    }
}