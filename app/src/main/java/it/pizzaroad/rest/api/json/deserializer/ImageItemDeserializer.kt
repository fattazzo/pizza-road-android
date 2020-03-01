/*
 * Project: Pizza Road
 * File: ImageItemDeserializer.kt
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

package it.pizzaroad.rest.api.json.deserializer

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import it.pizzaroad.rest.api.models.Image
import java.lang.reflect.Type

/**
 * Custom json deserializer.
 *
 * Image property ( es. for [it.pizzaroad.rest.api.models.Category] ) can be a JSONObject or JSONArray. Deserializer parse the value
 * and return a collection of Image.
 *
 * @return List of [Image]
 */
class ImageItemDeserializer : JsonDeserializer<List<Image>> {
    override fun deserialize(
        json: JsonElement,
        type: Type,
        context: JsonDeserializationContext?
    ): List<Image> {
        with(json) {
            return if (isJsonObject) {
                arrayListOf(Gson().fromJson(this, Image::class.java))
            } else {
                Gson().fromJson(this, Array<Image>::class.java).toList()
            }
        }
    }
}