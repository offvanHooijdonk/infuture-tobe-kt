package com.prediction.tobe.di

import android.app.Application
import android.content.Context
import com.prediction.tobe.presentation.presenter.ILoginPresenter
import com.prediction.tobe.presentation.presenter.LoginPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideLoginPresenter(): ILoginPresenter /*LoginPresenter*/ = LoginPresenter()
}