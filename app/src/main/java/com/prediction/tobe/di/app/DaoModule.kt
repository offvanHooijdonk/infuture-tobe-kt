package com.prediction.tobe.di.app

import com.prediction.tobe.data.db.firebase.PredictDao
import com.prediction.tobe.data.db.firebase.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaoModule {
    @Provides
    @Singleton
    fun provideUserDao(): UserDao = UserDao()

    @Provides
    @Singleton
    fun providePredictDao() = PredictDao()
}