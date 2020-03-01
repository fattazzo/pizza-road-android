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

package it.pizzaroad.activity.product

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import it.pizzaroad.R
import it.pizzaroad.activity.BaseActivity
import it.pizzaroad.app.PizzaRoadApplication
import it.pizzaroad.app.module.viewmodel.DaggerViewModelFactory
import it.pizzaroad.databinding.ActivityProductDetailsBinding
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
        const val EXTRA_PRODUCT_CATEGORY = "extraProductCategory"
    }

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var productViewModel: ProductViewModel

    override fun getLayoutResID(): Int = R.layout.activity_product_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PizzaRoadApplication).appComponent.inject(this)

        productViewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)
        binding.model = productViewModel

        setSupportActionBar(binding.appBarLayout.toolbar)

        productViewModel.product.observe(this, Observer {
            if(it.images.isNotEmpty()) {
                binding.imageView.load(it.images[0].src)
            }
        })

        val title = intent.extras?.getString(EXTRA_PRODUCT_CATEGORY)!!
        supportActionBar?.title = title

        val productId = intent.extras?.getInt(EXTRA_PRODUCT_ID)!!
        productViewModel.loadProduct(productId)
    }
}