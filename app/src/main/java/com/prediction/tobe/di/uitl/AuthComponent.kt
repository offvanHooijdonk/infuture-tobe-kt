package com.prediction.tobe.di.uitl

import com.prediction.tobe.di.presentation.LoginComponent
import com.prediction.tobe.di.presentation.LoginModule
import dagger.Subcomponent

@UtilScope
@Subcomponent(modules = arrayOf(AuthModule::class))
interface AuthComponent {
    fun plusLoginComponent(loginModule: LoginModule): LoginComponent
}