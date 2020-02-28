/*
 * Project: Pizza Road
 * File: OAuthInterceptor.kt
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
package it.pizzaroad.rest.api.oauth.interceptor

import android.util.Log
import it.pizzaroad.rest.api.oauth.constants.ParameterList
import it.pizzaroad.rest.api.oauth.service.HMACSha1SignatureService
import it.pizzaroad.rest.api.oauth.service.TimestampServiceImpl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class OAuthInterceptor private constructor(
    private val consumerKey: String,
    private val consumerSecret: String
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        Log.d("URL", original.url.toString())
        Log.d("URL", original.url.scheme)
        Log.d("encodedpath", original.url.encodedPath)
        Log.d("query", "" + original.url.query)
        Log.d("path", "" + original.url.host)
        Log.d("encodedQuery", "" + original.url.encodedQuery)
        Log.d("method", "" + original.method)
        ////////////////////////////////////////////////////////////
        val nonce = TimestampServiceImpl().nonce
        val timestamp = TimestampServiceImpl().timestampInSeconds
        Log.d("nonce", nonce)
        Log.d("time", timestamp)
        val dynamicStructureUrl =
            original.url.scheme + "://" + original.url.host + original.url.encodedPath
        Log.d("ENCODED PATH", "" + dynamicStructureUrl)
        val firstBaseString = original.method + "&" + urlEncoded(dynamicStructureUrl)
        Log.d("firstBaseString", firstBaseString)
        var generatedBaseString = ""
        generatedBaseString = if (original.url.encodedQuery != null) {
            original.url.encodedQuery + "&oauth_consumer_key=" + consumerKey + "&oauth_nonce=" + nonce + "&oauth_signature_method=HMAC-SHA1&oauth_timestamp=" + timestamp + "&oauth_version=1.0"
        } else {
            "oauth_consumer_key=$consumerKey&oauth_nonce=$nonce&oauth_signature_method=HMAC-SHA1&oauth_timestamp=$timestamp&oauth_version=1.0"
        }
        val result =
            ParameterList()
        result.addQuerystring(generatedBaseString)
        generatedBaseString = result.sort().asOauthBaseString()
        Log.d("Sorted", "00--" + result.sort().asOauthBaseString())
        var secoundBaseString = "&$generatedBaseString"
        if (firstBaseString.contains("%3F")) {
            Log.d("iff", "yess iff")
            secoundBaseString = "%26" + urlEncoded(generatedBaseString)
        }
        val baseString = firstBaseString + secoundBaseString
        val signature =
            HMACSha1SignatureService().getSignature(baseString, consumerSecret, "")
        Log.d("Signature", signature)
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(
                OAUTH_SIGNATURE_METHOD,
                OAUTH_SIGNATURE_METHOD_VALUE
            )
            .addQueryParameter(OAUTH_CONSUMER_KEY, consumerKey)
            .addQueryParameter(
                OAUTH_VERSION,
                OAUTH_VERSION_VALUE
            )
            .addQueryParameter(OAUTH_TIMESTAMP, timestamp)
            .addQueryParameter(OAUTH_NONCE, nonce)
            .addQueryParameter(OAUTH_SIGNATURE, signature)
            .build()
        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    fun urlEncoded(url: String?): String {
        var encodedurl = ""
        try {
            encodedurl = URLEncoder.encode(url, "UTF-8")
            Log.d("TEST", encodedurl)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return encodedurl
    }

    class Builder {
        private var consumerKey: String? = null
        private var consumerSecret: String? = null
        private val type = 0
        fun consumerKey(consumerKey: String?): Builder {
            if (consumerKey == null) throw NullPointerException("consumerKey = null")
            this.consumerKey = consumerKey
            return this
        }

        fun consumerSecret(consumerSecret: String?): Builder {
            if (consumerSecret == null) throw NullPointerException("consumerSecret = null")
            this.consumerSecret = consumerSecret
            return this
        }

        fun build(): OAuthInterceptor {
            checkNotNull(consumerKey) { "consumerKey not set" }
            checkNotNull(consumerSecret) { "consumerSecret not set" }
            return OAuthInterceptor(consumerKey!!, consumerSecret!!)
        }
    }

    companion object {
        private const val OAUTH_CONSUMER_KEY = "oauth_consumer_key"
        private const val OAUTH_NONCE = "oauth_nonce"
        private const val OAUTH_SIGNATURE = "oauth_signature"
        private const val OAUTH_SIGNATURE_METHOD = "oauth_signature_method"
        private const val OAUTH_SIGNATURE_METHOD_VALUE = "HMAC-SHA1"
        private const val OAUTH_TIMESTAMP = "oauth_timestamp"
        private const val OAUTH_VERSION = "oauth_version"
        private const val OAUTH_VERSION_VALUE = "1.0"
    }

}