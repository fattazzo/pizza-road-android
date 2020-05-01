/*
 * Project: Pizza Road
 * File: ProductViewModel.kt
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

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.pizzaroad.extensions.ioJob
import it.pizzaroad.rest.api.models.Product
import it.pizzaroad.rest.api.models.Variation
import it.pizzaroad.rest.manager.impl.ProductsManager
import java.math.BigDecimal
import javax.inject.Inject

/**
 * @author fattazzo
 *         <p/>
 *         date: 01/03/20
 */
class ProductViewModel @Inject constructor(private val productsManager: ProductsManager) :
    ViewModel() {

    val product = MutableLiveData<Product>(Product())

    val quantita = MutableLiveData<Int>(1)
    val variations = MutableLiveData<List<Variation>>(mutableListOf())

    val prezzo = MediatorLiveData<BigDecimal>().apply {
        value = BigDecimal.ZERO
        addSource(variations) { calcolaPrezzo() }
        addSource(quantita) { calcolaPrezzo() }
    }

    val loading = ObservableBoolean(false)

    fun loadProduct(productId: Int) {

        if (product.value?.id == productId) {
            return
        }

        loading.set(true)
        ioJob {
            try {
                val prodottoCaricato = productsManager.get(productId);
                product.postValue(prodottoCaricato)

                if (prodottoCaricato?.defaultAttributes.orEmpty().isNotEmpty()) {
                    val defAttributeIDs =
                        prodottoCaricato?.defaultAttributes?.map { "${it.id}|${it.name}|${it.option}" }
                            .orEmpty()
                    val defaultVariations = prodottoCaricato?.variations?.filter { variation ->
                        variation.attributes.map { "${it.id}|${it.name}|${it.option}" }
                            .containsAll(defAttributeIDs)
                    }.orEmpty().toMutableList()
                    variations.postValue(defaultVariations)
                }
            } finally {
                loading.set(false)
            }
        }
    }

    fun increaseQuantita() {
        quantita.postValue((quantita.value ?: 0) + 1)
    }

    fun decreaseQuantita() {
        if ((quantita.value ?: 0) > 1) {
            quantita.postValue((quantita.value ?: 0) - 1)
        }
    }

    private fun calcolaPrezzo() {
        val prezzoVariazioni = variations.value?.map { BigDecimal(it.price) }?.fold(BigDecimal.ZERO, BigDecimal::add) ?: BigDecimal.ZERO
        val qta = quantita.value ?: 1

        prezzo.postValue(prezzoVariazioni.multiply(qta.toBigDecimal()))
    }
}