package com.prediction.tobe.di

import com.prediction.tobe.presentation.ui.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    //fun plusAuthComponent(authModule: AuthModule): AuthComponent
    fun inject(loginActivity: LoginActivity)
}

