package com.prediction.tobe.presentation.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.prediction.tobe.AppToBe
import com.prediction.tobe.R
import com.prediction.tobe.presentation.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.longToast
import javax.inject.Inject



class LoginActivity : AppCompatActivity(), ILoginView {
    @Suppress("CONST_VAL_NOT_TOP_LEVEL_OR_OBJECT")
    private const val RC_SIGN_IN: Int = 10001

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        AppToBe.di.loginComponent().inject(this)

        presenter.attachView(this)
        presenter.onViewCreated()

        btnGoogleAccount.setOnClickListener({
            presenter.onGoogleAuthSelected()
        })
    }

    override fun showAuthOptions(show: Boolean) {
        if (show) {
            blockLogo.visibility = View.GONE
            blockAuth.visibility = View.VISIBLE
        } else {
            blockAuth.visibility = View.GONE
            blockLogo.visibility = View.VISIBLE
        }
    }

    override fun startGoogleAuthView(apiClient: GoogleApiClient) {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun proceedAsLogged() {
        longToast("Works!")
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        longToast("Connection Error " + connectionResult.errorMessage)
    }
}