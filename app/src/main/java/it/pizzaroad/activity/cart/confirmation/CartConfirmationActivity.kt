/*
 * Project: Pizza Road
 * File: CartConfirmationActivity.kt
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

package it.pizzaroad.activity.cart.confirmation

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.paypal.android.sdk.payments.*
import it.pizzaroad.BuildConfig
import it.pizzaroad.R
import it.pizzaroad.activity.BaseActivity
import it.pizzaroad.activity.pizzeria.PizzeriaActivity
import it.pizzaroad.app.PizzaRoadApplication
import it.pizzaroad.app.cart.AppCartManager
import it.pizzaroad.app.module.viewmodel.DaggerViewModelFactory
import it.pizzaroad.app.session.AppSessionManager
import it.pizzaroad.databinding.ActivityCartConfirmationBinding
import it.pizzaroad.databinding.converters.CurrencyConverter
import it.pizzaroad.extensions.TAG
import it.pizzaroad.openapi.models.OrderDetails
import it.pizzaroad.openapi.models.ShippingMethodType
import it.pizzaroad.rest.error.ErrorHandler
import kotlinx.android.synthetic.main.cart_title.view.*
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject


/**
 * @author fattazzo
 *         <p/>
 *         date: 18/05/20
 */
class CartConfirmationActivity : BaseActivity<ActivityCartConfirmationBinding>() {

    companion object {
        const val PAYPAL_REQUEST_CODE = 7777
    }

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var viewModel: CartConfirmationViewModel

    override fun getLayoutResID(): Int = R.layout.activity_cart_confirmation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PizzaRoadApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CartConfirmationViewModel::class.java)
        binding.model = viewModel

        binding.cartTitleLayout.order_title.text = resources.getString(R.string.activity_cart_order_name)
        binding.cartTitleLayout.total_title.text =
            CurrencyConverter.toString(this, AppCartManager.getCurrent(this).total)

        viewModel.customDateRequest.observe(this, Observer { binding.timePicker.isEnabled = it })
        viewModel.dateRequest.observe(this, Observer {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.timePicker.hour = it.hour
                binding.timePicker.minute = it.minute
            } else {
                binding.timePicker.currentHour = it.hour
                binding.timePicker.currentMinute = it.minute
            }
        })

        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            viewModel.updateRequestTime(hourOfDay, minute)
        }

        viewModel.initData(this)
    }

    fun sendOrder(view: View) {
        val order = AppCartManager.getCurrent(this)
        when (order.shippingMethod.type) {
            ShippingMethodType.PAY_PAL -> processPayment(order)
            ShippingMethodType.CASH -> createOrder()
            else -> Toast.makeText(this, "Tipo pagamento non gestito", Toast.LENGTH_SHORT).show()
        }
    }

    private fun processPayment(order: OrderDetails) {
        val payPalPayment = PayPalPayment(
            order.total,
            "EUR",
            "Ordine ${order.total.toPlainString()} utente ${AppSessionManager.getUser(this)?.username}",
            PayPalPayment.PAYMENT_INTENT_SALE
        )
        val config = PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(BuildConfig.PAYPAL_CLIENT_ID)
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment)
        startActivityForResult(intent, PAYPAL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            PAYPAL_REQUEST_CODE -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val confirmation: PaymentConfirmation? =
                            data?.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION)

                        if (checkPaymentConfirmation(confirmation)) {
                            createOrder()
                        }
                    }
                    Activity.RESULT_CANCELED ->
                        Toast.makeText(this, "Pagamento annullato", Toast.LENGTH_SHORT).show()
                    PaymentActivity.RESULT_EXTRAS_INVALID ->
                        Toast.makeText(this, "Pagamento non valido", Toast.LENGTH_SHORT).show()
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun checkPaymentConfirmation(confirmation: PaymentConfirmation?): Boolean {
        if (confirmation == null) {
            return false
        }

        var dialogResultIcon = R.drawable.ic_baseline_error_outline
        var dialogResultTitle = "Pagamento non eseguito"
        var dialogResultMessage =
            "Sembra essere sorto qualche problema durante la fase di conferma del pagamento. Si prega di contattare Pizza Road per l'assistenza, grazie"

        var result = false
        try {
            val paymentDetails = confirmation.toJSONObject().toString(4)
            Log.i(this.TAG, "PayPal payment result: $paymentDetails")

            val jsonObject = JSONObject(paymentDetails)
            val response = jsonObject.getJSONObject("response")
            val transactionId = response.getString("id")

            if (transactionId.isNotBlank()) {
                Toast.makeText(this, "Pagamento eseguito. ID transazione: $transactionId", Toast.LENGTH_SHORT).show()
                AppCartManager.updateTransactionId(this, transactionId)

                dialogResultIcon = R.drawable.ic_baseline_check
                dialogResultTitle = "Pagamento eseguito correttamente"
                dialogResultMessage =
                    "Il pagamento è andato a buon fine e l'ordine varrà ora inviato.\nIl numero della transazione di PayPal relativa al pagamento è\n\n$transactionId"
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            result = false
        } finally {
            MaterialDialog(this).show {
                title(text = dialogResultTitle)
                message(text = dialogResultMessage)
                icon(dialogResultIcon)
                positiveButton {}
            }
        }

        return result
    }

    private fun createOrder() {
        viewModel.createOrder(this, {
            Toast.makeText(this, "Ordine creato!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, PizzeriaActivity::class.java))
            finish();
        }, {
            ErrorHandler.showError(this, it)
        })
    }
}