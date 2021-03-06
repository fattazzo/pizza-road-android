/*
 * Project: Pizza Road
 * File: ErrorDataExtension.kt
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

package it.pizzaroad.rest.extensions

import android.content.Context
import androidx.core.text.HtmlCompat
import it.pizzaroad.openapi.models.ErrorData

/**
 * @author fattazzo
 *         <p/>
 *         date: 18/05/20
 */
fun ErrorData.toHtmlString(context: Context,includeTitle: Boolean = true): CharSequence {
    return if(includeTitle) {
        HtmlCompat.fromHtml("<b>${userTitle}</b><br/><i>${userMessage}</i><br/><br/>${toHtmlConstraintErrorString(context)}",HtmlCompat.FROM_HTML_MODE_LEGACY)
    } else {
        HtmlCompat.fromHtml("<i>${userMessage}</i><br/><br/>${toHtmlConstraintErrorString(context)}",HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

private fun ErrorData.toHtmlConstraintErrorString(context: Context): CharSequence {
    return constraintErrors.orEmpty().joinToString(
        separator = "<br/>",
        prefix = "<ul>",
        postfix = "</ul>",
        transform = { err ->
            "<b>${context.resources.getString(context.resources.getIdentifier(err.fieldName,"string",context.packageName))}</b><br>${err.constraintsNotRespected.joinToString(
                prefix = "<li>",
                postfix = "</li>",
                transform = { it })}"
        })
}