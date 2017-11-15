package com.prediction.tobe.di.uitl

import com.prediction.tobe.di.presentation.LoginComponent
import dagger.Subcomponent

@UtilScope
@Subcomponent(modules = arrayOf(AuthModule::class))
interface AuthComponent {
    fun plusLoginComponent(): LoginComponent
}