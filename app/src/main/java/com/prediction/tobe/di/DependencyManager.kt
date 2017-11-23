package com.prediction.tobe.di

import android.content.Context
import com.prediction.tobe.di.app.*
import com.prediction.tobe.di.presentation.login.LoginComponent
import com.prediction.tobe.di.presentation.login.LoginModule
import com.prediction.tobe.di.presentation.main.MainComponent
import com.prediction.tobe.di.presentation.main.MainModule
import com.prediction.tobe.di.uitl.AuthModule

class DependencyManager(ctx: Context) {
    companion object {
        const val DB_USERS = "database_reference_users"
    }

    private val graph: AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(ctx))
            .firebaseAuthModule(FirebaseAuthModule())
            .daoModule(DaoModule())
            .build()

    fun loginComponent(): LoginComponent = graph.plusAuthComponent(AuthModule()).plusLoginComponent(LoginModule())

    fun mainComponent(): MainComponent = graph.plusMainComponent(MainModule())
}