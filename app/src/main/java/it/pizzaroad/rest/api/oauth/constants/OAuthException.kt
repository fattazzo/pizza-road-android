/*
 * Project: Pizza Road
 * File: OAuthException.kt
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

/**
 * Default scribe exception.
 * Represents a problem in the OAuth signing process
 */
open class OAuthException : RuntimeException {
    /**
     * Default constructor
     * @param message message explaining what went wrong
     * @param e original exception
     */
    constructor(message: String?, e: Exception?) : super(message, e) {}

    /**
     * No-exception constructor. Used when there is no original exception
     *
     * @param message message explaining what went wrong
     */
    constructor(message: String?) : super(message, null) {}

    companion object {
        private const val serialVersionUID = 1L
    }
}