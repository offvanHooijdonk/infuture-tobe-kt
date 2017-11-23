package com.prediction.tobe.di.app

import com.prediction.tobe.data.db.firebase.IUserDao
import com.prediction.tobe.data.db.firebase.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaoModule {
    @Provides
    @Singleton
    fun provideUserDao(): IUserDao = UserDao()
}