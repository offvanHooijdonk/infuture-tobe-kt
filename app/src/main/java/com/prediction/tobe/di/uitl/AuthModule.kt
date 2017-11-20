package com.prediction.tobe.di.uitl

import android.content.Context
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.prediction.tobe.R
import com.prediction.tobe.data.auth.FireBaseAuthUtil
import dagger.Module
import dagger.Provides

@Module
class AuthModule {
    @Provides
    @UtilScope
    fun provideAuthenticator() : FireBaseAuthUtil = FireBaseAuthUtil()

    @Provides
    @UtilScope
    fun provideAuthApiClient(ctx: Context) : GoogleApiClient.Builder {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(ctx.getString(R.string.web_client_id))
                .requestEmail()
                .build()

        return GoogleApiClient.Builder(ctx)
                /*.enableAutoManage(view as FragmentActivity, view::onConnectionFailed)*/
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                /*.build()*/
    }
}