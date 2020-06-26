/*
 * Project: Pizza Road
 * File: PizzaViewModel.kt
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

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.pizzaroad.extensions.TAG
import it.pizzaroad.extensions.ioJob
import it.pizzaroad.openapi.models.ItemPizza
import it.pizzaroad.openapi.models.ItemPizzaPrice
import it.pizzaroad.openapi.models.VariationDough
import it.pizzaroad.rest.manager.impl.PizzasManager
import java.math.BigDecimal
import javax.inject.Inject

/**
 * @author fattazzo
 *         <p/>
 *         date: 01/03/20
 */
class PizzaViewModel @Inject constructor(private val pizzasManager: PizzasManager) : ViewModel() {

    val pizza = MutableLiveData(ItemPizza())

    val quantita = MutableLiveData(1)

    val dough = MutableLiveData<VariationDough>(null)

    val variation = MutableLiveData<ItemPizzaPrice>(null)

    val toppings = MediatorLiveData<MutableList<ToppingExtraDto>>().apply {
        value = mutableListOf()
        addSource(dough) { buildToppingExtraDto() }
        addSource(variation) { buildToppingExtraDto() }
    }

    val price = MediatorLiveData<BigDecimal>().apply {
        value = BigDecimal.ZERO
        addSource(pizza) { calcolaPrezzo() }
        addSource(quantita) { calcolaPrezzo() }
        addSource(dough) { calcolaPrezzo() }
        addSource(variation) { calcolaPrezzo() }
        addSource(toppings) { calcolaPrezzo() }
    }

    val loading = ObservableBoolean(false)

    fun loadPizza(pizzaId: Int) {

        if (pizza.value?.id == pizzaId) {
            return
        }

        loading.set(true)
        ioJob {
            try {
                val pizzaLoaded = pizzasManager.getPizza(pizzaId).value
                pizza.postValue(pizzaLoaded ?: ItemPizza())

                dough.postValue(pizzaLoaded?.doughs?.firstOrNull())
                variation.postValue(pizzaLoaded?.prices?.firstOrNull())
                toppings.postValue(mutableListOf())
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
        val variationPrice = variation.value?.price ?: BigDecimal.ZERO
        val doughExtra = dough.value?.extra ?: BigDecimal.ZERO
        val toppingsExtras = toppings.value?.filter { dto -> dto.selected.get() }?.map { it.toppingExtra.extra }
            ?.fold(BigDecimal.ZERO, BigDecimal::add) ?: BigDecimal.ZERO

        val qta = quantita.value ?: 1

        price.postValue((variationPrice.add(doughExtra).add(toppingsExtras)).multiply(qta.toBigDecimal()))
    }

    private fun buildToppingExtraDto() {
        var toppingsExtra = pizza.value?.toppingExtras.orEmpty()

        dough.value?.let { toppingsExtra = toppingsExtra.filter { te -> te.dough == it } }
        variation.value?.let { toppingsExtra = toppingsExtra.filter { te -> te.variationSize == it.variationSize } }

        val currentToppings = toppings.value.orEmpty()
        toppings.postValue(toppingsExtra.map { te ->
            val dto = ToppingExtraDto(te)

            val idx = currentToppings.indexOf(dto)
            if (idx != -1) {
                dto.selected = currentToppings[idx].selected
            }
            Log.e(TAG, dto.toppingId.toString())
            dto
        }.toMutableList())
        calcolaPrezzo()
    }

    fun updateTopping(topping: ToppingExtraDto) {
        val index = toppings.value?.indexOf(topping);
        if (index != -1) {
            toppings.value?.set(index!!, topping)
            calcolaPrezzo()
        }
    }

    fun isDataValid(): Boolean {
        val pizzaValid = pizza.value != null
        val quantityValid = quantita.value != null && quantita.value!! > 0
        val priceValid = price.value != null && price.value!! > BigDecimal.ZERO
        val doughValid = dough.value != null
        val variationValid = variation.value != null
        return pizzaValid && quantityValid && priceValid && doughValid && variationValid
    }
}