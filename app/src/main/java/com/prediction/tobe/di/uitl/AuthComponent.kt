package com.prediction.tobe.di.uitl

import com.prediction.tobe.di.presentation.login.LoginComponent
import com.prediction.tobe.di.presentation.login.LoginModule
import dagger.Subcomponent

@UtilScope
@Subcomponent(modules = arrayOf(AuthModule::class))
interface AuthComponent {
    fun plusLoginComponent(loginModule: LoginModule): LoginComponent
}