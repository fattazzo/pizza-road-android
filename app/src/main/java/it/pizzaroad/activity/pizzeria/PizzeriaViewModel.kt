/*
 * Project: Pizza Road
 * File: PizzeriaViewModel.kt
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

package it.pizzaroad.activity.pizzeria

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.pizzaroad.extensions.ioJob
import it.pizzaroad.openapi.models.Category
import it.pizzaroad.openapi.models.Item
import it.pizzaroad.openapi.models.ItemType
import it.pizzaroad.rest.manager.impl.CategoriesManager
import it.pizzaroad.rest.manager.impl.PizzasManager
import it.pizzaroad.rest.manager.impl.ProductsManager
import javax.inject.Inject

/**
 * @author fattazzo
 *         <p/>
 *         date: 28/02/20
 */
class PizzeriaViewModel @Inject constructor(
    private val categoriesManager: CategoriesManager,
    private val productsManager: ProductsManager,
    private val pizzasManager: PizzasManager
) :
    ViewModel() {

    val categories = MutableLiveData<List<Category>>()
    val selectedCategory = MutableLiveData<Category>()

    val items = MediatorLiveData<List<Item>>().apply {
        addSource(selectedCategory) { loadCategoryItems() }
    }

    val loadingData = ObservableBoolean(false)

    fun loadCategories(forceRefresh: Boolean = false) {

        if (categories.value.orEmpty().isNotEmpty() && !forceRefresh) {
            return
        }

        loadingData.set(true)
        ioJob {
            try {
                val result = categoriesManager.getCategories()
                categories.postValue(result.value)
            } finally {
                loadingData.set(false)
            }
        }
    }

    private fun loadCategoryItems() {

        if (selectedCategory.value == null) {
            items.postValue(listOf())
            return
        }

        loadingData.set(true)
        ioJob {
            try {
                val result = if (selectedCategory.value!!.type == ItemType.PRODUCT) {
                    productsManager.getProducts(selectedCategory.value!!.id)
                } else {
                    pizzasManager.getPizzas(selectedCategory.value!!.id)
                }
                items.postValue(result.value ?: listOf())
            } finally {
                loadingData.set(false)
            }
        }
    }
}