/*
 * Project: Pizza Road
 * File: RetrofitBuilder.kt
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

package it.pizzaroad.rest

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import it.pizzaroad.app.AppConstants
import it.pizzaroad.app.AppPreferences
import it.pizzaroad.app.session.AppSessionManager
import it.pizzaroad.extensions.TAG
import it.pizzaroad.rest.converters.OffsetDateTimeConverter
import it.pizzaroad.rest.extensions.log
import okhttp3.OkHttpClient
import org.threeten.bp.OffsetDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.*
import javax.security.cert.CertificateException


object RetrofitBuilder {

    var baseUrl = AppConstants.REST_API_BASE_URL

    var socketFactory: SSLSocketFactory? = null
    var trustManager: X509TrustManager? = null

    private var secureClient: Retrofit? = null

    private val httpClientBuilder: OkHttpClient.Builder
        get() {
            val builder = OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)

            builder.hostnameVerifier(HostnameVerifier { _, _ -> true })

            applySocketFactoryAndTrustManager(builder)

            return builder
        }

    val gson : Gson by lazy {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeConverter())
        gsonBuilder.create()
    }

    val client: Retrofit by lazy {
        val httpClient = httpClientBuilder
        httpClient.addInterceptor { chain ->
            chain.request().log(TAG)
            chain.proceed(chain.request())
        }

        Retrofit.Builder().client(httpClient.build()).baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    fun getSecureClient(context: Context): Retrofit {
        if (secureClient == null) {
            val httpClient = httpClientBuilder
            httpClient.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        "Authorization",
                        "Bearer ${AppSessionManager.getAccessToken(context).orEmpty()}"
                    )
                    .addHeader("Accept-Language", AppPreferences.getLocale(context))
                    .build()
                request.log(TAG)
                chain.proceed(request)
            }
            httpClient.addInterceptor(RefreshTokenInterceptor(context))

            secureClient = Retrofit.Builder().client(httpClient.build()).baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        return secureClient!!
    }

    private fun applySocketFactoryAndTrustManager(builder: OkHttpClient.Builder) {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(
                chain: Array<java.security.cert.X509Certificate>,
                authType: String
            ) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(
                chain: Array<java.security.cert.X509Certificate>,
                authType: String
            ) {
            }

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                return arrayOf()
            }
        })

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        sslContext.getSocketFactory()

        val socketToUse = if (socketFactory == null) sslContext.socketFactory else socketFactory
        val trustToUse: X509TrustManager =
            if (trustManager == null) (trustAllCerts[0] as X509TrustManager) else trustManager!!

        builder.sslSocketFactory(socketToUse!!, trustToUse)
    }
}