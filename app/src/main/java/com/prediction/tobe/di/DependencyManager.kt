package com.prediction.tobe.di

import android.content.Context
import com.prediction.tobe.di.presentation.LoginComponent
import com.prediction.tobe.di.presentation.LoginModule
import com.prediction.tobe.di.uitl.AuthModule

class DependencyManager(ctx: Context) {
    private val graph: AppComponent = DaggerAppComponent.builder().appModule(AppModule(ctx)).build()

    fun loginComponent(): LoginComponent = graph.plusAuthComponent(AuthModule()).plusLoginComponent(LoginModule())

}