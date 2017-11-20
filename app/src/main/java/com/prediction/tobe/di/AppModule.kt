package com.prediction.tobe.di

import android.content.Context
import dagger.Module
import javax.inject.Singleton

@Module
class AppModule(private val app: Context) {
    @Singleton
    fun provideContext(): Context = app
}