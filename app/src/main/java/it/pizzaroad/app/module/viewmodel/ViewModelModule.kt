/*
 * Project: Pizza Road
 * File: ViewModelModule.kt
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

package it.pizzaroad.app.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import it.pizzaroad.activity.account.AccountViewModel
import it.pizzaroad.activity.account.orders.OrdersViewModel
import it.pizzaroad.activity.auth.login.LoginViewModel
import it.pizzaroad.activity.cart.confirmation.CartConfirmationViewModel
import it.pizzaroad.activity.cart.order.CartOrderViewModel
import it.pizzaroad.activity.cart.shippingmethod.CartShippingMethodsViewModel
import it.pizzaroad.activity.items.pizza.PizzaViewModel
import it.pizzaroad.activity.items.product.ProductViewModel
import it.pizzaroad.activity.pizzeria.PizzeriaViewModel

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PizzeriaViewModel::class)
    internal abstract fun providePizzeriaViewModel(viewModel: PizzeriaViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    internal abstract fun provideProductViewModel(viewModel: ProductViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PizzaViewModel::class)
    internal abstract fun providePizzaViewModel(viewModel: PizzaViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun provideLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartOrderViewModel::class)
    internal abstract fun provideCartOrderViewModel(viewModel: CartOrderViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartShippingMethodsViewModel::class)
    internal abstract fun provideCartShippingMethodsViewModel(viewModel: CartShippingMethodsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartConfirmationViewModel::class)
    internal abstract fun provideCartConfirmationViewModel(viewModel: CartConfirmationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    internal abstract fun provideAccountViewModel(viewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OrdersViewModel::class)
    internal abstract fun provideOrdersViewModel(viewModel: OrdersViewModel): ViewModel
}