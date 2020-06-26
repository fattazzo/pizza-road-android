/*
 * Project: Pizza Road
 * File: AccountActivity.kt
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

package it.pizzaroad.activity.account

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.google.android.material.textfield.TextInputEditText
import it.pizzaroad.R
import it.pizzaroad.activity.BaseActivity
import it.pizzaroad.app.PizzaRoadApplication
import it.pizzaroad.app.module.viewmodel.DaggerViewModelFactory
import it.pizzaroad.app.session.AppSessionManager
import it.pizzaroad.databinding.ActivityAccountBinding
import it.pizzaroad.openapi.models.Address
import it.pizzaroad.openapi.models.DeliveryAddress
import it.pizzaroad.widget.views.DeliveryAddressView
import kotlinx.android.synthetic.main.app_bar_main.view.*
import javax.inject.Inject

/**
 * @author fattazzo
 *         <p/>
 *         date: 15/05/20
 */
class AccountActivity : BaseActivity<ActivityAccountBinding>() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var viewModel: AccountViewModel

    override fun getLayoutResID(): Int = R.layout.activity_account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PizzaRoadApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(AccountViewModel::class.java)
        binding.model = viewModel

        setSupportActionBar(binding.appBarLayout.toolbar)

        buildDeliveryAddressesViews()
        viewModel.user.observe(this, Observer { buildDeliveryAddressesViews() })

        viewModel.init(AppSessionManager.getUser(this)?.username)
    }

    private fun buildDeliveryAddressesViews() {
        binding.deliveryAddressesLayout.removeAllViews()

        viewModel.user.value?.deliveryAddresses?.forEach {
            val deliveryAddressView = DeliveryAddressView(this)
            deliveryAddressView.bind(it)
            binding.deliveryAddressesLayout.addView(deliveryAddressView)
        }
    }

    fun addDeliveryAddress(view: View) {
        MaterialDialog(this, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            title(text = "_nuovo indirizzo_")
            customView(R.layout.delivery_address_view, scrollable = true, horizontalPadding = true)
            noAutoDismiss()
            positiveButton(android.R.string.ok) { dialog ->
                // Pull the password out of the custom view when the positive button is pressed
                val streetInput: TextInputEditText = dialog.getCustomView().findViewById(R.id.address_street_view)
                val numberInput: TextInputEditText = dialog.getCustomView().findViewById(R.id.address_number_view)
                val placeInput: TextInputEditText = dialog.getCustomView().findViewById(R.id.address_place_view)
                val codeInput: TextInputEditText = dialog.getCustomView().findViewById(R.id.address_cap_view)
                if (streetInput.text.toString().isBlank() || placeInput.text.toString().isBlank()) {
                    Toast.makeText(this@AccountActivity, "_via e città sono obbligatori_", Toast.LENGTH_SHORT).show()
                } else {
                    val address =
                        Address().streetAddress(streetInput.text.toString()).number(numberInput.text.toString())
                            .place(placeInput.text.toString()).postalCode(codeInput.text.toString())
                    val deliveryAddress = DeliveryAddress().address(address)
                    viewModel.addDeliveryAddress(deliveryAddress)
                    dialog.dismiss()
                }
            }
            negativeButton(android.R.string.cancel) { dialog -> dialog.dismiss() }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.account, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> viewModel.saveUser()
        }
        return super.onOptionsItemSelected(item)
    }
}