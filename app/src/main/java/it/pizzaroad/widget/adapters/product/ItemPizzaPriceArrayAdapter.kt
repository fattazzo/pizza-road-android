/*
 * Project: Pizza Road
 * File: ItemPizzaPriceArrayAdapter.kt
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

package it.pizzaroad.widget.adapters.product

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import it.pizzaroad.R
import it.pizzaroad.openapi.models.ItemPizzaPrice
import it.pizzaroad.widget.views.ItemPizzaPriceView

/**
 * @author fattazzo
 *         <p/>
 *         date: 04/05/20
 */
class ItemPizzaPriceArrayAdapter(context: Context, objects: MutableList<ItemPizzaPrice>) :
    ArrayAdapter<ItemPizzaPrice>(context, R.layout.list_item_pizza_price, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        bindView(position, convertView, true)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
        bindView(position, convertView)

    private fun bindView(position: Int, convertView: View?, hideDescription: Boolean = false): View {
        val view: ItemPizzaPriceView = (convertView ?: ItemPizzaPriceView(context)) as ItemPizzaPriceView

        view.bind(getItem(position))
        if (hideDescription) {
            view.binding.descriptionTextView.visibility = View.GONE
        }
        return view
    }
}