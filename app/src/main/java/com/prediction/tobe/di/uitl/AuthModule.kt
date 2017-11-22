package com.prediction.tobe.di.uitl

import com.prediction.tobe.data.auth.FireBaseAuthUtil
import dagger.Module
import dagger.Provides

@Module
class AuthModule {
    @Provides
    @UtilScope
    fun provideAuthenticator() : FireBaseAuthUtil = FireBaseAuthUtil()

    /*@Provides
    @UtilScope
    fun provideAuthApiClient(ctx: Context) : GoogleApiClient.Builder {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(ctx.getString(R.string.web_client_id))
                .requestEmail()
                .build()

        return GoogleApiClient.Builder(ctx)
                *//*.enableAutoManage(view as FragmentActivity, view::onConnectionFailed)*//*
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                *//*.build()*//*
    }*/
}