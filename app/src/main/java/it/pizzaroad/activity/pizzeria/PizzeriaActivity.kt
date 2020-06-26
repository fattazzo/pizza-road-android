/*
 * Project: Pizza Road
 * File: PizzeriaActivity.kt
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

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.material.navigation.NavigationView
import it.pizzaroad.R
import it.pizzaroad.activity.BaseActivity
import it.pizzaroad.activity.account.AccountActivity
import it.pizzaroad.activity.account.orders.OrdersActivity
import it.pizzaroad.activity.cart.order.CartOrderActivity
import it.pizzaroad.activity.preferences.PreferencesActivity
import it.pizzaroad.app.PizzaRoadApplication
import it.pizzaroad.app.cart.AppCartManager
import it.pizzaroad.app.module.viewmodel.DaggerViewModelFactory
import it.pizzaroad.app.session.AppSessionManager
import it.pizzaroad.databinding.ActivityPizzeriaBinding
import it.pizzaroad.widget.adapters.product.ProductRecyclerViewAdapter
import javax.inject.Inject


class PizzeriaActivity : BaseActivity<ActivityPizzeriaBinding>(),
    NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var viewModel: PizzeriaViewModel

    private val productRecyclerViewAdapter = ProductRecyclerViewAdapter(mutableListOf())

    private var cartItemMenu: MenuItem? = null

    override fun getLayoutResID(): Int = R.layout.activity_pizzeria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PizzaRoadApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PizzeriaViewModel::class.java)
        binding.model = viewModel

        initToolbar(binding.toolbar)

        binding.toolbarLayout.setExpandedTitleColor(Color.TRANSPARENT)

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        setupNavigationView()

        binding.contentPizzeriaContainer.categoryProductsRecyclerView.setHasFixedSize(true)
        binding.contentPizzeriaContainer.categoryProductsRecyclerView.layoutManager =
            LinearLayoutManager(binding.root.context)
        binding.contentPizzeriaContainer.categoryProductsRecyclerView.adapter =
            productRecyclerViewAdapter
        viewModel.items.observe(this, Observer { productRecyclerViewAdapter.setProducts(it) })

        viewModel.loadCategories()

        updateCartMenuItem()
    }

    private fun setupNavigationView() {
        binding.navView.setNavigationItemSelectedListener(this)
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.nav_header_username).text = AppSessionManager.getUser(this)?.username ?: ""
        binding.navView.getHeaderView(0).findViewById<Button>(R.id.nav_header_logout_button).setOnClickListener {
            AppSessionManager.setSessionData(this,null)
            showLoginIfNeeded()
        }
    }

    override fun onResume() {
        super.onResume()
        updateCartMenuItem()
    }

    override fun onUserLoggedIn() {
        setupNavigationView()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_account -> startActivity(Intent(this, AccountActivity::class.java))
            R.id.nav_orders -> startActivity(Intent(this, OrdersActivity::class.java))
            R.id.nav_preferences -> startActivity(Intent(this, PreferencesActivity::class.java))
        }
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        cartItemMenu = menu.findItem(R.id.action_cart)
        cartItemMenu?.setActionView(R.layout.cart_notification_layout)
        cartItemMenu?.actionView?.setOnClickListener(){onOptionsItemSelected(cartItemMenu!!)}
        updateCartMenuItem()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cart -> startActivity(Intent(this, CartOrderActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateCartMenuItem() {
        if (cartItemMenu == null) {
            return
        }

        val orderDetails = AppCartManager.getCurrent(this)

        if (orderDetails.lines.isEmpty()) {
            cartItemMenu?.actionView?.findViewById<TextView>(R.id.txtCount)?.visibility = View.GONE
        } else {
            cartItemMenu?.actionView?.findViewById<TextView>(R.id.txtCount)?.visibility = View.VISIBLE
            cartItemMenu?.actionView?.findViewById<TextView>(R.id.txtCount)?.text = orderDetails.lines.size.toString()
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            MaterialDialog(this).show {
                icon(R.drawable.ic_pizza_road_logo)
                title(R.string.app_name)
                message(R.string.app_exit_dialog_message)
                positiveButton(R.string.yes) { super.onBackPressed() }
                negativeButton(R.string.no) { it.dismiss() }
            }
        }
    }
}