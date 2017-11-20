package com.prediction.tobe.di.uitl

import com.prediction.tobe.data.auth.FireBaseAuthUtil
import dagger.Module
import dagger.Provides

@Module
class AuthModule {
    @Provides
    @UtilScope
    fun provideAuthenticator() : FireBaseAuthUtil = FireBaseAuthUtil()
}