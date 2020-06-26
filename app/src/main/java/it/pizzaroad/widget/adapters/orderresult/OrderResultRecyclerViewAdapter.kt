/*
 * Project: Pizza Road
 * File: OrderResultRecyclerViewAdapter.kt
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

package it.pizzaroad.widget.adapters.orderresult

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import it.pizzaroad.databinding.ListItemOrderResultBinding
import it.pizzaroad.openapi.models.OrderSearchResult


class OrderResultRecyclerViewAdapter(
    private val orders: MutableList<OrderSearchResult>
) :
    RecyclerView.Adapter<OrderResultRecyclerViewAdapter.OrderResultViewHolder>() {

    var orderResultRecyclerViewAdapterListener: OrderResultRecyclerViewAdapterListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): OrderResultViewHolder {
        val binding =
            ListItemOrderResultBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return OrderResultViewHolder(binding)
    }

    override fun onBindViewHolder(orderResultViewHolder: OrderResultViewHolder, i: Int) {
        orderResultViewHolder.binding.order = orders[i]
        orderResultViewHolder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = orders.size

    fun setOrders(orders: List<OrderSearchResult>) {
        this.orders.clear()
        this.orders.addAll(orders.toMutableList())
        notifyDataSetChanged()
    }

    inner class OrderResultViewHolder(val binding: ListItemOrderResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val order = binding.order!!

                /*
                val b = Bundle()
                val intent = if(item.category.type == ItemType.PIZZA) {
                    b.putInt(PizzaActivity.EXTRA_PIZZA_ID, binding.item!!.id)
                    Intent(binding.root.context, PizzaActivity::class.java)
                } else {
                    b.putInt(ProductActivity.EXTRA_PRODUCT_ID, binding.item!!.id)
                    Intent(binding.root.context, ProductActivity::class.java)
                }

                intent.putExtras(b)
                startActivity(binding.root.context, intent, null)
                 */
            }
        }
    }

    interface OrderResultRecyclerViewAdapterListener {
        fun onListItemSelected(order: OrderSearchResult)
    }
}