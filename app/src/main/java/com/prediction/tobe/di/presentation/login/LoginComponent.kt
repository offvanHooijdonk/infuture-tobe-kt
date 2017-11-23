package com.prediction.tobe.di.presentation.login

import com.prediction.tobe.presentation.ui.login.LoginActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(LoginModule::class))
@LoginScope
interface LoginComponent {
    fun inject(loginActivity: LoginActivity)
}