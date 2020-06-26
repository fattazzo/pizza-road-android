/*
 * Project: Pizza Road
 * File: LoginActivity.kt
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

package it.pizzaroad.activity.auth.login

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import it.pizzaroad.R
import it.pizzaroad.activity.BaseActivity
import it.pizzaroad.activity.auth.login.social.google.GoogleSignInActivity
import it.pizzaroad.app.PizzaRoadApplication
import it.pizzaroad.app.module.viewmodel.DaggerViewModelFactory
import it.pizzaroad.app.session.AppSessionManager
import it.pizzaroad.databinding.ActivityLoginBinding
import it.pizzaroad.openapi.models.SocialTypeEnum
import javax.inject.Inject

/**
 * @author fattazzo
 *         <p/>
 *         date: 01/05/20
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    companion object {
        const val REQUEST_CODE_LOGIN = 2000
        const val REQUEST_CODE_SOCIAL_LOGIN = 2100

        const val RESULT_SOCIAL_LOGIN = 2001
        const val RESULT_SOCIAL_LOGOUT = 2002
        const val RESULT_SOCIAL_REMOVE = 2003

        const val EXTRA_LOGIN_TOKEN = "token"
        const val EXTRA_LOGIN_ID = "id"
        const val EXTRA_LOGIN_TYPE = "type"
    }

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var viewModel: LoginViewModel

    override fun getLayoutResID(): Int = R.layout.activity_login

    override fun checkLogin(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PizzaRoadApplication).appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LoginViewModel::class.java)
        binding.model = viewModel
    }

    fun login(view: View) {

        viewModel.login(
            {
                this.currentFocus?.let { v ->
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(v.windowToken, 0)
                }
                setResult(Activity.RESULT_OK, Intent())
                this@LoginActivity.finish()
            },
            {
                if (it.error == 403) {
                    /*
                    MaterialDialog(this).show {
                        title(R.string.login_user_not_activated_title)
                        message(R.string.login_user_not_activated_message)
                        icon(R.drawable.ic_error)
                        positiveButton {
                            val intent = Intent(this@LoginActivity, ActivationActivity::class.java)
                            intent.putExtra(
                                ActivationActivity.USERNAME_EXTRA,
                                loginViewModel.username.value
                            )
                            startActivity(intent)
                            this@LoginActivity.finish()
                        }
                    }
                    */
                } else {
                    //Toast.makeText(this, R.string.login_error, Toast.LENGTH_SHORT).show()
                    this@LoginActivity.recreate()
                }
            }
        )

        if (viewModel.dataValid.value == true) animateLoginButton()
    }

    fun loginSocial(token: String, id: String, type: SocialTypeEnum) {

        viewModel.loginSocial(token,id,type,
            {
                this.currentFocus?.let { v ->
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(v.windowToken, 0)
                }
                setResult(Activity.RESULT_OK, Intent())
                this@LoginActivity.finish()
            },
            {
                if (it.error == 403) {
                    /*
                    MaterialDialog(this).show {
                        title(R.string.login_user_not_activated_title)
                        message(R.string.login_user_not_activated_message)
                        icon(R.drawable.ic_error)
                        positiveButton {
                            val intent = Intent(this@LoginActivity, ActivationActivity::class.java)
                            intent.putExtra(
                                ActivationActivity.USERNAME_EXTRA,
                                loginViewModel.username.value
                            )
                            startActivity(intent)
                            this@LoginActivity.finish()
                        }
                    }
                    */
                } else {
                    //Toast.makeText(this, R.string.login_error, Toast.LENGTH_SHORT).show()
                    this@LoginActivity.recreate()
                }
            }
        )
        animateLoginButton()
    }

    fun openGoogleLoginActivity(view: View) {
        val intent = Intent(this@LoginActivity, GoogleSignInActivity::class.java)
        startActivityForResult(intent,REQUEST_CODE_SOCIAL_LOGIN)
    }

    private fun animateLoginButton() {

        // Width animation from button width to R.dimen.sign_button_loading_width in 250 millisec.
        val finalWidth = resources.getDimension(R.dimen.sign_button_loading_width).toInt()
        val anim = ValueAnimator.ofInt(binding.signInButton.measuredWidth, finalWidth)
        anim.addUpdateListener {
            val value: Int = it.animatedValue as Int
            val layoutParam = binding.signInButton.layoutParams
            layoutParam.width = value
            binding.signInButton.requestLayout()
        }
        anim.duration = 250
        anim.start()

        // Login text animation from alpha 1 to 0 and progress bar visibility at the end
        binding.signInText.animate().alpha(0f).setDuration(250)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
            ).start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE_SOCIAL_LOGIN) {
        when (resultCode) {
            RESULT_SOCIAL_LOGIN -> {
                val token = data!!.getStringExtra(EXTRA_LOGIN_TOKEN).orEmpty()
                val id = data.getStringExtra(EXTRA_LOGIN_ID).orEmpty()
                val type = SocialTypeEnum.fromValue(data.getStringExtra(EXTRA_LOGIN_TYPE)) ?: SocialTypeEnum.NONE
                loginSocial(token, id, type)
            }
            RESULT_SOCIAL_LOGOUT, RESULT_SOCIAL_REMOVE -> {
                AppSessionManager.setSessionData(this,null)
                //showLoginIfNeeded()
            }
        }} else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {
        when {
            binding.usernameET.isFocused -> binding.usernameET.clearFocus()
            binding.passwordET.isFocused -> binding.passwordET.clearFocus()
            else -> super.onBackPressed()
        }
    }
}