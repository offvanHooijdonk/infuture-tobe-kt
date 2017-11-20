package com.prediction.tobe.presentation.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.prediction.tobe.AppToBe
import com.prediction.tobe.R
import com.prediction.tobe.presentation.presenter.LoginPresenter
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
    }

    override fun proceedAsLogged() {
        longToast("Works!")
    }
}