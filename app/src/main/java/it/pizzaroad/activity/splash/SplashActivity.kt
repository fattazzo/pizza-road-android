/*
 * Project: Pizza Road
 * File: SplashActivity.kt
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

package it.pizzaroad.activity.splash

import android.content.Intent
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import it.pizzaroad.R
import it.pizzaroad.activity.pizzeria.PizzeriaActivity

/**
 * @author fattazzo
 *         <p/>
 *         date: 28/02/20
 */
class SplashActivity: AppCompatActivity(R.layout.splash) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        Handler().postDelayed({
            startActivity(Intent(this,PizzeriaActivity::class.java))
            this.finish()
        },1000)
    }
}