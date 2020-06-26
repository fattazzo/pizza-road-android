/*
 * Project: Pizza Road
 * File: ToppingsExtraDtoRecyclerViewAdapter.kt
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

package it.pizzaroad.widget.adapters.toppingsExtra

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.pizzaroad.activity.items.pizza.ToppingExtraDto
import it.pizzaroad.widget.views.BindableView
import it.pizzaroad.widget.views.ToppingExtraDtoView

/**
 * @author fattazzo
 *         <p/>
 *         date: 04/05/20
 */
class ToppingsExtraDtoRecyclerViewAdapter(
    private val context: Context,
    var onItemClickListener: OnItemClickListener? = null
) :
    RecyclerView.Adapter<ToppingsExtraDtoRecyclerViewAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: ToppingExtraDto?)
    }

    class ViewHolder(val view: BindableView<ToppingExtraDto?>) : RecyclerView.ViewHolder(view as View) {
        var item: ToppingExtraDto? = null
    }

    var items: List<ToppingExtraDto> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val holder = ViewHolder(ToppingExtraDtoView(context))
        (holder.view as View).layoutParams = lp
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.bind(items[position])
        holder.item = items[position]
        onItemClickListener?.let {
            (holder.view as ToppingExtraDtoView).setOnClickListener {
                (it as ToppingExtraDtoView).binding.dto?.selected?.set(!(it.binding.dto?.selected?.get() ?: true))
                onItemClickListener?.onItemClick(holder.item)
            }
        }
    }
}