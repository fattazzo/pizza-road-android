/*
 * Project: Pizza Road
 * File: GoogleSignInActivity.kt
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

package it.pizzaroad.activity.auth.login.social.google

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import it.pizzaroad.R
import it.pizzaroad.activity.BaseActivity
import it.pizzaroad.activity.auth.login.LoginActivity.Companion.EXTRA_LOGIN_ID
import it.pizzaroad.activity.auth.login.LoginActivity.Companion.EXTRA_LOGIN_TOKEN
import it.pizzaroad.activity.auth.login.LoginActivity.Companion.EXTRA_LOGIN_TYPE
import it.pizzaroad.activity.auth.login.LoginActivity.Companion.RESULT_SOCIAL_LOGIN
import it.pizzaroad.activity.auth.login.LoginActivity.Companion.RESULT_SOCIAL_LOGOUT
import it.pizzaroad.activity.auth.login.LoginActivity.Companion.RESULT_SOCIAL_REMOVE
import it.pizzaroad.databinding.ActivitySigninGoogleBinding
import it.pizzaroad.openapi.models.SocialTypeEnum


@SuppressLint("RestrictedApi")
open class GoogleSignInActivity : BaseActivity<ActivitySigninGoogleBinding>() {

    private var mGoogleSignInClient: GoogleSignInClient? = null

    override fun getLayoutResID(): Int = R.layout.activity_signin_google

    override fun checkLogin(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.signInButton.setSize(SignInButton.SIZE_STANDARD)
        binding.signInButton.setColorScheme(SignInButton.COLOR_LIGHT)
        binding.signInButton.setOnClickListener { signIn() }

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(resources.getString(R.string.google_client_id))
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    public override fun onStart() {
        super.onStart()

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account, false)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account, false)
            val returnIntent = Intent()
            returnIntent.putExtra(EXTRA_LOGIN_TOKEN, account!!.idToken)
            returnIntent.putExtra(EXTRA_LOGIN_ID, account.id)
            returnIntent.putExtra(EXTRA_LOGIN_TYPE, SocialTypeEnum.GOOGLE.value)
            setResult(RESULT_SOCIAL_LOGIN, returnIntent)
            finish()
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null, true)
        }

    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun signOut(view: View) {
        mGoogleSignInClient!!.signOut()
            .addOnCompleteListener(this) {
                updateUI(null, false)
                setResult(RESULT_SOCIAL_LOGOUT)
                finish()
            }
    }

    fun disconnect(view: View) {
        mGoogleSignInClient!!.revokeAccess()
            .addOnCompleteListener(this) {
                updateUI(null, false)
                setResult(RESULT_SOCIAL_REMOVE)
                finish()
            }
    }

    private fun updateUI(account: GoogleSignInAccount?, hasError: Boolean) {
        if (account != null) {
            binding.statusView.setText("_signed_in_")
            binding.signInButton.visibility = View.GONE
            binding.signOutAndDisconnectLayout.visibility = View.VISIBLE
        } else {
            binding.statusView.setText("_signed_out_")
            binding.signInButton.visibility = View.VISIBLE
            binding.signOutAndDisconnectLayout.visibility = View.GONE
        }
        //binding.accountView.bind(account)

        if (hasError) {
            binding.statusView.setText("_sign_in_error_")
        }
    }

    companion object {

        private const val TAG = "GoogleSignInActivity"
        private const val RC_SIGN_IN = 9001
    }
}
