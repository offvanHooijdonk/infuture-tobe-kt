package com.prediction.tobe.di.app

import com.prediction.tobe.di.uitl.AuthComponent
import com.prediction.tobe.di.uitl.AuthModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, FirebaseAuthModule::class))
interface AppComponent {

    fun plusAuthComponent(authModule: AuthModule): AuthComponent
}

