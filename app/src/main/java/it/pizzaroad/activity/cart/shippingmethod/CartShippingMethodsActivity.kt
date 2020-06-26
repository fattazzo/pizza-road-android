/*
 * Project: Pizza Road
 * File: CartShippingMethodsActivity.kt
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

package it.pizzaroad.activity.cart.shippingmethod

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import it.pizzaroad.R
import it.pizzaroad.activity.BaseActivity
import it.pizzaroad.activity.cart.confirmation.CartConfirmationActivity
import it.pizzaroad.app.PizzaRoadApplication
import it.pizzaroad.app.cart.AppCartManager
import it.pizzaroad.app.module.viewmodel.DaggerViewModelFactory
import it.pizzaroad.databinding.ActivityCartShippingMethodsBinding
import it.pizzaroad.databinding.converters.CurrencyConverter
import it.pizzaroad.openapi.models.Address
import it.pizzaroad.openapi.models.ShippingMethod
import it.pizzaroad.openapi.models.ShippingType
import kotlinx.android.synthetic.main.cart_title.view.*
import javax.inject.Inject

/**
 * @author fattazzo
 *         <p/>
 *         date: 15/05/20
 */
class CartShippingMethodsActivity : BaseActivity<ActivityCartShippingMethodsBinding>() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var viewModel: CartShippingMethodsViewModel

    override fun getLayoutResID(): Int = R.layout.activity_cart_shipping_methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PizzaRoadApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CartShippingMethodsViewModel::class.java)
        binding.model = viewModel

        buildShippingMethodsViews()
        buildShippingTypeViews(viewModel.shippingMethod.value)
        buildShippingAddressViews()

        binding.cartTitleLayout.order_title.text = resources.getString(R.string.activity_cart_order_name)
        binding.cartTitleLayout.total_title.text =
            CurrencyConverter.toString(this, AppCartManager.getCurrent(this).total)

        viewModel.shippingMethod.observe(this, Observer { buildShippingTypeViews(it) })
        viewModel.availableShippingMethods.observe(this, Observer { buildShippingMethodsViews() })
        viewModel.availableDeliveryAddresses.observe(this, Observer { buildShippingAddressViews() })

        viewModel.initData(this)
    }

    private fun buildShippingMethodsViews() {
        binding.shippingMethodsGroup.setOnCheckedChangeListener(null)
        binding.shippingMethodsGroup.removeAllViews()

        val selectedSM = viewModel.shippingMethod.value
        viewModel.availableShippingMethods.value?.forEach {
            val radioButton = RadioButton(this)
            radioButton.id = it.id
            radioButton.text =
                HtmlCompat.fromHtml("<b>${it.title}</b><br/><i>${it.description}</i>", HtmlCompat.FROM_HTML_MODE_LEGACY)
            radioButton.tag = it
            radioButton.isChecked = selectedSM != null && selectedSM.id == it.id
            binding.shippingMethodsGroup.addView(radioButton)
        }

        binding.shippingMethodsGroup.setOnCheckedChangeListener { radioGroup, _ ->
            val radioButton: RadioButton = radioGroup.findViewById(radioGroup.checkedRadioButtonId)
            viewModel.updateShippingMethod(this, radioButton.tag as ShippingMethod)
        }
    }

    private fun buildShippingTypeViews(shippingMethod: ShippingMethod?) {
        binding.shippingTypeGroup.setOnCheckedChangeListener(null)
        binding.shippingTypeGroup.clearCheck()
        binding.shippingTypeGroup.removeAllViews()

        val selectedST = viewModel.shippingType.value
        shippingMethod?.shippingTypes?.forEach {
            val radioButton = RadioButton(this)
            radioButton.id = it.ordinal
            radioButton.text =
                resources.getString(resources.getIdentifier("ShippingType.${it.name}", "string", packageName))
            radioButton.tag = it
            radioButton.isChecked = selectedST != null && selectedST == it
            binding.shippingTypeGroup.addView(radioButton)
        }

        binding.shippingTypeGroup.setOnCheckedChangeListener { radioGroup, _ ->
            val radioButton: RadioButton = radioGroup.findViewById(radioGroup.checkedRadioButtonId)
            viewModel.updateShippingType(this, radioButton.tag as ShippingType)
        }
    }

    private fun buildShippingAddressViews() {
        binding.addressesGroup.setOnCheckedChangeListener(null)
        binding.addressesGroup.clearCheck()
        binding.addressesGroup.removeAllViews()

        val selectedAdd = viewModel.deliveryAddress.value
        viewModel.availableDeliveryAddresses.value?.forEachIndexed { idx, address ->
            val radioButton = RadioButton(this)
            radioButton.id = idx
            radioButton.text = "${address.streetAddress} ${address.number}\n${address.place} ${address.postalCode}"
            radioButton.tag = address
            radioButton.isChecked = selectedAdd != null && selectedAdd == address
            binding.addressesGroup.addView(radioButton)
        }

        binding.addressesGroup.setOnCheckedChangeListener { radioGroup, _ ->
            val radioButton: RadioButton = radioGroup.findViewById(radioGroup.checkedRadioButtonId)
            viewModel.updateAddress(this, radioButton.tag as Address)
        }
    }

    fun goToNextStep(view: View) {
        startActivity(Intent(this, CartConfirmationActivity::class.java))
    }

    fun goToBackStep(view: View) {
        this.finish()
    }
}