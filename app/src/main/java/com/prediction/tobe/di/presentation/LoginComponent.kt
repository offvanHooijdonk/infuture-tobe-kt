package com.prediction.tobe.di.presentation

import com.prediction.tobe.presentation.ui.ILoginView
import dagger.Subcomponent

@LoginScope
@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginComponent {
    fun inject(loginActivity: ILoginView)
}