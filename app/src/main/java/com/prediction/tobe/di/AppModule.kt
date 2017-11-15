package com.prediction.tobe.di

import android.app.Application
import android.content.Context
import dagger.Module
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Singleton
    fun provideContext(): Context = app
}