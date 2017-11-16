package com.prediction.tobe.di.presentation

import com.prediction.tobe.presentation.ui.ILoginView
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(LoginModule::class))
@LoginScope
interface LoginComponent {
    fun inject(loginActivity: ILoginView)
}