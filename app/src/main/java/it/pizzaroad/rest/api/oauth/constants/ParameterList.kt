/*
 * Project: Pizza Road
 * File: ParameterList.kt
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

import android.util.Log
import it.pizzaroad.rest.api.oauth.constants.OAuthEncoder.decode
import it.pizzaroad.rest.api.oauth.constants.OAuthEncoder.encode
import java.util.*

class ParameterList {
    private val params: MutableList<Parameter>

    constructor() {
        params = ArrayList()
    }

    internal constructor(params: List<Parameter>?) {
        this.params = ArrayList(params!!)
    }

    constructor(map: Map<String?, String?>) : this() {
        for ((key, value) in map) {
            params.add(Parameter(key!!, value!!))
        }
    }

    fun add(key: String?, value: String?) {
        params.add(Parameter(key!!, value!!))
    }

    fun appendTo(url: String): String { //        Preconditions.checkNotNull(url, "Cannot append to null URL");
        var url = url
        val queryString = asFormUrlEncodedString()
        return if (queryString == EMPTY_STRING) {
            url
        } else {
            url += if (url.indexOf(QUERY_STRING_SEPARATOR) != -1) PARAM_SEPARATOR else QUERY_STRING_SEPARATOR
            url += queryString
            url
        }
    }

    fun asOauthBaseString(): String {
        return encode(asFormUrlEncodedString())
    }

    fun asFormUrlEncodedString(): String {
        if (params.size == 0) return EMPTY_STRING
        val builder = StringBuilder()
        for (p in params) {
            builder.append('&').append(p.asUrlEncodedPair())
        }
        return builder.toString().substring(1)
    }

    fun addAll(other: ParameterList) {
        params.addAll(other.params)
    }

    fun addQuerystring(queryString: String?) {
        if (queryString != null && queryString.length > 0) {
            for (param in queryString.split(PARAM_SEPARATOR).toTypedArray()) {
                val pair =
                    param.split(PAIR_SEPARATOR)
                        .toTypedArray()
                val key = decode(pair[0])
                val value =
                    if (pair.size > 1) decode(pair[1]) else EMPTY_STRING
                params.add(Parameter(key, value))
            }
        }
    }

    operator fun contains(param: Parameter?): Boolean {
        return params.contains(param)
    }

    fun size(): Int {
        return params.size
    }

    fun sort(): ParameterList {
        val sorted =
            ParameterList(params)
        Log.d("saoted", sorted.params[0].asUrlEncodedPair())
        Log.d("saoted", sorted.params[1].asUrlEncodedPair())
        Collections.sort(sorted.params)
        Log.d("saoted", sorted.params[0].asUrlEncodedPair())
        Log.d("saoted", sorted.params[1].asUrlEncodedPair())
        return sorted
    }

    companion object {
        private const val QUERY_STRING_SEPARATOR = '?'
        private const val PARAM_SEPARATOR = "&"
        private const val PAIR_SEPARATOR = "="
        private const val EMPTY_STRING = ""
    }
}