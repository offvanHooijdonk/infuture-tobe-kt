package com.prediction.tobe.di.app

import com.prediction.tobe.di.presentation.predict.PredictComponent
import com.prediction.tobe.di.presentation.predict.PredictModule
import com.prediction.tobe.di.uitl.AuthComponent
import com.prediction.tobe.di.uitl.AuthModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, FirebaseAuthModule::class, DaoModule::class))
interface AppComponent {
    fun plusAuthComponent(authModule: AuthModule): AuthComponent

    fun plusPredictComponent(predictModule: PredictModule): PredictComponent
}

