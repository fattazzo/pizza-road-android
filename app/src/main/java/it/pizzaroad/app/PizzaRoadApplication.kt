/*
 * Project: Pizza Road
 * File: PizzaRoadApplication.kt
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

package it.pizzaroad.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import de.mindpipe.android.logging.log4j.LogConfigurator
import it.pizzaroad.app.component.AppComponent
import it.pizzaroad.app.component.DaggerAppComponent
import it.pizzaroad.app.module.AppModule
import org.apache.log4j.Level


/**
 * @author fattazzo
 *         <p/>
 *         date: 28/02/20
 */
class PizzaRoadApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        initAppComponent()

        val logConfigurator = LogConfigurator()
        logConfigurator.isUseFileAppender = false
        logConfigurator.rootLevel = Level.DEBUG
        logConfigurator.configure()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}