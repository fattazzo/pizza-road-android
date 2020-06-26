/*
 * Project: Pizza Road
 * File: PizzaActivity.kt
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

package it.pizzaroad.activity.items.pizza

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import it.pizzaroad.R
import it.pizzaroad.activity.BaseActivity
import it.pizzaroad.app.PizzaRoadApplication
import it.pizzaroad.app.cart.AppCartManager
import it.pizzaroad.app.module.viewmodel.DaggerViewModelFactory
import it.pizzaroad.databinding.ActivityPizzaDetailsBinding
import it.pizzaroad.openapi.models.ItemPizzaPrice
import it.pizzaroad.openapi.models.VariationDough
import it.pizzaroad.widget.adapters.dough.DoughArrayAdapter
import it.pizzaroad.widget.adapters.product.ItemPizzaPriceArrayAdapter
import it.pizzaroad.widget.adapters.toppingsExtra.ToppingsExtraDtoRecyclerViewAdapter
import kotlinx.android.synthetic.main.app_bar_main.view.*
import javax.inject.Inject


/**
 * @author fattazzo
 *         <p/>
 *         date: 01/03/20
 */
class PizzaActivity : BaseActivity<ActivityPizzaDetailsBinding>(),
    ToppingsExtraDtoRecyclerViewAdapter.OnItemClickListener {

    companion object {
        const val EXTRA_PIZZA_ID = "extraPizzaId"
    }

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var pizzaViewModel: PizzaViewModel

    private val doughsAdapter: DoughArrayAdapter by lazy { DoughArrayAdapter(this, mutableListOf()) }
    private val pricesAdapter: ItemPizzaPriceArrayAdapter by lazy { ItemPizzaPriceArrayAdapter(this, mutableListOf()) }
    private val toppingExtraAdapter: ToppingsExtraDtoRecyclerViewAdapter by lazy {
        ToppingsExtraDtoRecyclerViewAdapter(this, this)
    }

    override fun getLayoutResID(): Int = R.layout.activity_pizza_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PizzaRoadApplication).appComponent.inject(this)

        pizzaViewModel =
            ViewModelProvider(this, viewModelFactory).get(PizzaViewModel::class.java)
        binding.model = pizzaViewModel

        setSupportActionBar(binding.appBarLayout.toolbar)

        pizzaViewModel.pizza.observe(this, Observer {
            supportActionBar?.title = it.name
            if (it.imageUrl != null) {
                binding.imageView.load(it.imageUrl)
            } else {
                binding.imageView.load(R.drawable.ic_pizza_road_logo_gray)
            }

            doughsAdapter.clear()
            doughsAdapter.addAll(it.doughs ?: mutableListOf())

            pricesAdapter.clear()
            pricesAdapter.addAll(it.prices ?: mutableListOf())
        })

        bindDoughs()
        bindPrices()
        bindToppingsExtra()

        val productId = intent.extras?.getInt(EXTRA_PIZZA_ID)!!
        pizzaViewModel.loadPizza(productId)
    }

    private fun bindDoughs() {
        binding.variationDoughs.spinnerDoughs.adapter = doughsAdapter

        pizzaViewModel.dough.observe(this, Observer {
            val currentItem = binding.variationDoughs.spinnerDoughs.selectedItem as VariationDough?
            if (it != null && it.id != currentItem?.id) {
                binding.variationDoughs.spinnerDoughs.setSelection(doughsAdapter.getPosition(it))
            }
        })

        binding.variationDoughs.spinnerDoughs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                pizzaViewModel.dough.postValue(parent?.getItemAtPosition(position) as VariationDough?)
            }
        }
    }

    private fun bindPrices() {
        binding.spinnerPrices.adapter = pricesAdapter

        pizzaViewModel.variation.observe(this, Observer {
            val currentItem = binding.spinnerPrices.selectedItem as ItemPizzaPrice?
            if (it != null && it.id != currentItem?.id) {
                binding.spinnerPrices.setSelection(pricesAdapter.getPosition(it))
            }
        })

        binding.spinnerPrices.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Non faccio niente
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                pizzaViewModel.variation.postValue(parent?.getItemAtPosition(position) as ItemPizzaPrice?)
            }
        }
    }

    fun addPizzaToCart(view: View) {
        if (pizzaViewModel.isDataValid()) {
            AppCartManager.addPizzaItem(
                this,
                pizzaViewModel.pizza.value!!,
                pizzaViewModel.quantita.value!!,
                pizzaViewModel.price.value!!,
                pizzaViewModel.variation.value!!,
                pizzaViewModel.dough.value!!,
                pizzaViewModel.toppings.value?.filter { dto -> dto.selected.get() }?.map { dto -> dto.toppingExtra }
            )
            this.finish()
        }
    }

    private fun bindToppingsExtra() {
        binding.variationToppings.toppingsExtraRecyclerView.adapter = toppingExtraAdapter
        binding.variationToppings.toppingsExtraRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        pizzaViewModel.toppings.observe(this, Observer {
            toppingExtraAdapter.items = it.orEmpty()
        })
    }

    override fun onItemClick(item: ToppingExtraDto?) {
        item?.let {
            pizzaViewModel.updateTopping(item)
        }
    }
}