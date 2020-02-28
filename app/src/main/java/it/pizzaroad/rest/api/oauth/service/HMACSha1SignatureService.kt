/*
 * Project: Pizza Road
 * File: HMACSha1SignatureService.kt
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
package it.pizzaroad.rest.api.oauth.service

import android.util.Base64
import android.util.Log
import it.pizzaroad.rest.api.oauth.constants.OAuthEncoder
import it.pizzaroad.rest.api.oauth.constants.OAuthSignatureException
import it.pizzaroad.rest.api.oauth.utils.Preconditions.checkEmptyString
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * SHA1 SignatureGenerator
 */
class HMACSha1SignatureService : SignatureService {
    /**
     * {@inheritDoc}
     */
    override fun getSignature(
        baseString: String?,
        apiSecret: String?,
        tokenSecret: String?
    ): String? {
        return try {
            checkEmptyString(
                baseString,
                "Base string cant be null or empty string"
            )
            checkEmptyString(
                apiSecret,
                "Api secret cant be null or empty string"
            )
            doSign(
                baseString,
                OAuthEncoder.encode(apiSecret) + '&' + OAuthEncoder.encode(tokenSecret)
            )
        } catch (e: Exception) {
            throw OAuthSignatureException(baseString, e)
        }
    }

    @Throws(Exception::class)
    private fun doSign(toSign: String?, keyString: String): String {
        Log.d("is it signing", "----------------------$toSign")
        Log.d("is 22222222", keyString + "")
        val key = SecretKeySpec(
            keyString.toByteArray(charset(UTF8)),
            HMAC_SHA1
        )
        val mac =
            Mac.getInstance(HMAC_SHA1)
        mac.init(key)
        val bytes =
            mac.doFinal(toSign!!.toByteArray(charset(UTF8)))
        return bytesToBase64String(bytes).replace(
            CARRIAGE_RETURN,
            EMPTY_STRING
        )
    }

    private fun bytesToBase64String(bytes: ByteArray): String {
        return Base64.encodeToString(bytes, Base64.NO_WRAP)
    }

    override val signatureMethod: String?
        get() = METHOD

    companion object {
        private const val EMPTY_STRING = ""
        private const val CARRIAGE_RETURN = "\r\n"
        private const val UTF8 = "UTF-8"
        private const val HMAC_SHA1 = "HmacSHA1"
        private const val METHOD = "HMAC-SHA1"
    }
}