package com.prediction.tobe.di

import com.prediction.tobe.di.uitl.AuthComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun plusAuthComponent(): AuthComponent
}

