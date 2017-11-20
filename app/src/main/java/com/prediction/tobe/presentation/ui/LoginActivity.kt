package com.prediction.tobe.presentation.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.prediction.tobe.AppToBe
import com.prediction.tobe.R
import com.prediction.tobe.presentation.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.longToast
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), ILoginView {


    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        AppToBe.di.loginComponent().inject(this)

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

    override fun proceedAsLogged() {
        longToast("Works!")
    }
}