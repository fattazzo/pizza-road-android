/*
 * Project: Pizza Road
 * File: BaseActivity.kt
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

package it.pizzaroad.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import it.pizzaroad.activity.auth.login.LoginActivity
import it.pizzaroad.app.session.AppSessionManager

/**
 * @author fattazzo
 *         <p/>
 *         date: 28/02/20
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    /**
     * Activity data binding
     */
    val binding: T by lazy {
        val bind = DataBindingUtil.setContentView<T>(this, getLayoutResID())
        bind.lifecycleOwner = this
        bind
    }

    /**
     * Activity layout ID
     * @return activity layout ID
     */
    protected abstract fun getLayoutResID(): Int

    protected fun initToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LoginActivity.REQUEST_CODE_LOGIN && resultCode == Activity.RESULT_OK) {
            onUserLoggedIn()
        }
    }

    protected open fun onUserLoggedIn() {}

    protected open fun checkLogin(): Boolean = true

    override fun onResume() {
        super.onResume()
        if (checkLogin()) showLoginIfNeeded()
    }

    fun showLoginIfNeeded() {
        if (AppSessionManager.getAccessToken(this) == null || AppSessionManager.getRefreshToken(this) == null) {
            val intent = Intent(this.applicationContext, LoginActivity::class.java)
            this.startActivityForResult(intent, LoginActivity.REQUEST_CODE_LOGIN)
        }
    }
}