package com.prediction.tobe.presentation.presenter

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseUser
import com.prediction.tobe.data.db.firebase.UserNotFoundException
import com.prediction.tobe.data.interactor.AuthInteractor
import com.prediction.tobe.domain.UserBean
import com.prediction.tobe.presentation.ui.ILoginView
import com.prediction.tobe.session.SessionHelper
import javax.inject.Inject

class LoginPresenter @Inject constructor() : ILoginPresenter {
    @Inject
    lateinit var ctx: Context
    @Inject
    lateinit var interactor: AuthInteractor
    @Inject
    lateinit var session: SessionHelper

    lateinit var view: ILoginView

    override fun onViewCreated() {
        val user: FirebaseUser? = interactor.getCurrentUser()
        if (user != null) {
            interactor.loadUserInfo(user)
                    .subscribe({ onSuccessfulAuth() }, this::onUserLoadError)
        } else {
            view.showAuthOptions(true)
        }
    }

    override fun onGoogleAuthSelected() {
        val signInOptions = interactor.getSignInOptions()

        val apiClient = GoogleApiClient.Builder(ctx)
                .enableAutoManage(view as FragmentActivity, view::onConnectionFailed)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build()

        view.startGoogleAuthView(apiClient)
    }

    override fun attachView(view: ILoginView) {
        this.view = view
    }

    override fun onGoogleAuthReturn(data: Intent?) {
        if (data != null) {
            view.showAuthProgress(true)
            interactor.signInWithGoogle(data)
                    .subscribe({ onSuccessfulAuth() }, { onError("Error while authorizing with Google", it) })
        } else {
            // todo handle
        }
    }

    private fun onSuccessfulAuth() {
        view.showAuthProgress(false)
        view.navigateToMain()
    }

    private fun onError(msg: String, th: Throwable) {
        Log.e("LOG", msg, th)
    }

    private fun onUserLoadError(th: Throwable) {
        if (th is UserNotFoundException) {
            interactor.signOut()
            view.showAuthProgress(false)
            view.showAuthOptions(true)
            view.showUserNotFound()
        }
    }

    // todo move this to a Model Converter
    private fun toUserBean(fu: FirebaseUser): UserBean =
            UserBean(fu.uid, fu.displayName, fu.photoUrl?.toString(), fu.email)
}
