/*
 * Project: Pizza Road
 * File: ProductActivity.kt
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

package it.pizzaroad.activity.items.product

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import it.pizzaroad.R
import it.pizzaroad.activity.BaseActivity
import it.pizzaroad.app.PizzaRoadApplication
import it.pizzaroad.app.cart.AppCartManager
import it.pizzaroad.app.module.viewmodel.DaggerViewModelFactory
import it.pizzaroad.databinding.ActivityProductDetailsBinding
import it.pizzaroad.openapi.models.ItemProductPrice
import it.pizzaroad.widget.adapters.product.ItemProductPriceArrayAdapter
import kotlinx.android.synthetic.main.app_bar_main.view.*
import javax.inject.Inject


/**
 * @author fattazzo
 *         <p/>
 *         date: 01/03/20
 */
class ProductActivity : BaseActivity<ActivityProductDetailsBinding>() {

    companion object {
        const val EXTRA_PRODUCT_ID = "extraProductId"
    }

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var productViewModel: ProductViewModel

    private val pricesAdapter: ItemProductPriceArrayAdapter by lazy {
        ItemProductPriceArrayAdapter(
            this,
            mutableListOf()
        )
    }

    override fun getLayoutResID(): Int = R.layout.activity_product_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PizzaRoadApplication).appComponent.inject(this)

        productViewModel =
            ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)
        binding.model = productViewModel

        setSupportActionBar(binding.appBarLayout.toolbar)

        productViewModel.product.observe(this, Observer {
            supportActionBar?.title = it.name
            if (it.imageUrl != null) {
                binding.imageView.load(it.imageUrl)
            } else {
                binding.imageView.load(R.drawable.ic_pizza_road_logo_gray)
            }

            pricesAdapter.clear()
            pricesAdapter.addAll(it.prices ?: mutableListOf())
        })

        bindPrices()

        val productId = intent.extras?.getInt(EXTRA_PRODUCT_ID)!!
        productViewModel.loadProduct(productId)
    }

    private fun bindPrices() {
        binding.spinnerPrices.adapter = pricesAdapter

        productViewModel.variation.observe(this, Observer {
            val currentItem = binding.spinnerPrices.selectedItem as ItemProductPrice?
            if (it != null && it.id != currentItem?.id) {
                binding.spinnerPrices.setSelection(pricesAdapter.getPosition(it))
            }
        })

        binding.spinnerPrices.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                productViewModel.variation.postValue(parent?.getItemAtPosition(position) as ItemProductPrice?)
            }
        }
    }

    fun addProductToCart(view: View) {
        if (productViewModel.isDataValid()) {
            AppCartManager.addProductItem(
                this,
                productViewModel.product.value!!,
                productViewModel.quantita.value!!,
                productViewModel.price.value!!,
                productViewModel.variation.value!!
            )
            this.finish()
        }
    }
}