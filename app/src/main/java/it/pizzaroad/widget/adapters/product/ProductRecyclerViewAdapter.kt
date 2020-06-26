/*
 * Project: Pizza Road
 * File: ProductRecyclerViewAdapter.kt
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

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import it.pizzaroad.R
import it.pizzaroad.activity.items.pizza.PizzaActivity
import it.pizzaroad.activity.items.product.ProductActivity
import it.pizzaroad.databinding.ListItemItemBinding
import it.pizzaroad.openapi.models.Item
import it.pizzaroad.openapi.models.ItemType


class ProductRecyclerViewAdapter(
    private val items: MutableList<Item>
) :
    RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    var productRecyclerViewAdapterListener: ProductRecyclerViewAdapterListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ProductViewHolder {
        val binding =
            ListItemItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(productViewHolder: ProductViewHolder, i: Int) {
        productViewHolder.binding.item = items[i]
        productViewHolder.binding.executePendingBindings()

        if(items[i].imageUrl != null) {
            productViewHolder.binding.imageView.load(items[i].imageUrl)
        } else {
            productViewHolder.binding.imageView.load(R.drawable.ic_pizza_road_logo_gray)
        }
    }

    override fun getItemCount(): Int = items.size

    fun setProducts(items: List<Item>) {
        this.items.clear()
        this.items.addAll(items.toMutableList())
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(val binding: ListItemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val item = binding.item!!

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
            }
        }
    }

    interface ProductRecyclerViewAdapterListener {
        fun onListItemSelected(product: Item)
    }
}