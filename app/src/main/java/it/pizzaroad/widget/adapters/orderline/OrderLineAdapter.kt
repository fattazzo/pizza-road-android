/*
 * Project: Pizza Road
 * File: OrderLineAdapter.kt
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

package it.pizzaroad.widget.adapters.orderline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.pizzaroad.databinding.ListItemOrderLinePizzaBinding
import it.pizzaroad.databinding.ListItemOrderLineProductBinding
import it.pizzaroad.openapi.models.OrderLine

/**
 * @author fattazzo
 *         <p/>
 *         date: 14/05/20
 */
class OrderLineAdapter(private val items: MutableList<OrderLine>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_PIZZA = 1
        const val TYPE_PRODUCT = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_PIZZA -> {
                val binding =
                    ListItemOrderLinePizzaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PizzaViewHolder(binding)
            }
            TYPE_PRODUCT -> {
                val binding =
                    ListItemOrderLineProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ProductViewHolder(binding)
            } else -> throw RuntimeException("Order line type not managed")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(items[position].pizzaPrice != null) TYPE_PIZZA else TYPE_PRODUCT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)) {
            TYPE_PIZZA -> {
                (holder as PizzaViewHolder).binding.line = items[position]
                holder.binding.executePendingBindings()
            }
            TYPE_PRODUCT -> {
                (holder as ProductViewHolder).binding.line = items[position]
                holder.binding.executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun setLines(items: List<OrderLine>) {
        this.items.clear()
        this.items.addAll(items.toMutableList())
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(val binding: ListItemOrderLineProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
    inner class PizzaViewHolder(val binding: ListItemOrderLinePizzaBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}