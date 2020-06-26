/*
 * Project: Pizza Road
 * File: OrdersActivity.kt
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

package it.pizzaroad.activity.account.orders

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import it.pizzaroad.R
import it.pizzaroad.activity.BaseActivity
import it.pizzaroad.app.PizzaRoadApplication
import it.pizzaroad.app.module.viewmodel.DaggerViewModelFactory
import it.pizzaroad.databinding.ActivityOrdersBinding
import it.pizzaroad.widget.adapters.orderresult.OrderResultRecyclerViewAdapter
import kotlinx.android.synthetic.main.app_bar_main.view.*
import javax.inject.Inject

/**
 * @author fattazzo
 *         <p/>
 *         date: 17/06/20
 */
class OrdersActivity: BaseActivity<ActivityOrdersBinding>() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var viewModel: OrdersViewModel

    private val ordersResultRecyclerViewAdapter = OrderResultRecyclerViewAdapter(mutableListOf())

    override fun getLayoutResID(): Int = R.layout.activity_orders

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PizzaRoadApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(OrdersViewModel::class.java)
        binding.model = viewModel

        setSupportActionBar(binding.appBarLayout.toolbar)

        binding.ordersRecyclerView.setHasFixedSize(true)
        binding.ordersRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.ordersRecyclerView.adapter = ordersResultRecyclerViewAdapter
        viewModel.orders.observe(this, Observer { ordersResultRecyclerViewAdapter.setOrders(it) })

        viewModel.searchOrders()
    }
}