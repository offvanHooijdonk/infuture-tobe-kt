package com.prediction.tobe.presentation.ui

import android.app.Activity
import android.content.Intent
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
import org.jetbrains.anko.alert
import org.jetbrains.anko.longToast
import org.jetbrains.anko.okButton
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), ILoginView {

    companion object {
        private const val RC_SIGN_IN = 10001
    }

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        AppToBe.di.loginComponent().inject(this)

        showAuthOptions(false)

        btnGoogleAccount.setOnClickListener({
            presenter.onGoogleAuthSelected()
        })

        presenter.attachView(this)
        presenter.onViewCreated()


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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == RC_SIGN_IN) {
            presenter.onGoogleAuthReturn(data);
        }
    }

    override fun showAuthProgress(show: Boolean) {
        if (show) {
            btnGoogleAccount.isEnabled = false
            btnChooseLogin.isEnabled = false
            prbLogin.visibility = View.VISIBLE
        } else {
            btnGoogleAccount.isEnabled = false
            btnChooseLogin.isEnabled = false
            prbLogin.visibility = View.INVISIBLE
        }
    }

    override fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        longToast("Connection Error " + connectionResult.errorMessage)
    }

    override fun showUserNotFound() {
        alert("User you are trying to sign in with is not found. Try to login again.", "") {
            okButton {  }
        }
    }
}