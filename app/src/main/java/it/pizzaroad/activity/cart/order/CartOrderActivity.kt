/*
 * Project: Pizza Road
 * File: CartOrderActivity.kt
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

package it.pizzaroad.activity.cart.order

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import it.pizzaroad.R
import it.pizzaroad.activity.BaseActivity
import it.pizzaroad.activity.cart.shippingmethod.CartShippingMethodsActivity
import it.pizzaroad.app.PizzaRoadApplication
import it.pizzaroad.app.module.viewmodel.DaggerViewModelFactory
import it.pizzaroad.databinding.ActivityCartOrderBinding
import it.pizzaroad.databinding.converters.CurrencyConverter
import it.pizzaroad.widget.adapters.orderline.OrderLineAdapter
import kotlinx.android.synthetic.main.cart_title.view.*
import javax.inject.Inject

/**
 * @author fattazzo
 *         <p/>
 *         date: 14/05/20
 */
class CartOrderActivity: BaseActivity<ActivityCartOrderBinding>() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var viewModel: CartOrderViewModel

    private val orderLineAdapter = OrderLineAdapter(mutableListOf())

    override fun getLayoutResID(): Int = R.layout.activity_cart_order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PizzaRoadApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CartOrderViewModel::class.java)
        binding.model = viewModel

        binding.orderLinesRecyclerView.setHasFixedSize(true)
        binding.orderLinesRecyclerView.layoutManager =
            LinearLayoutManager(binding.root.context)
        binding.orderLinesRecyclerView.adapter = orderLineAdapter

        viewModel.order.observe(this, Observer {
            binding.cartTitleLayout.order_title.text = resources.getString(R.string.activity_cart_order_name)
            binding.cartTitleLayout.total_title.text = CurrencyConverter.toString(this,it.total)
            orderLineAdapter.setLines(it.lines)
        })

        viewModel.load(this)
    }

    fun goToNextStep(view: View) {
        startActivity(Intent(this, CartShippingMethodsActivity::class.java))
    }
}