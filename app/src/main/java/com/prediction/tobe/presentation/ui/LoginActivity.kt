package com.prediction.tobe.presentation.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.prediction.tobe.R
import com.prediction.tobe.getLoginComponent
import com.prediction.tobe.presentation.presenter.ILoginPresenter
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), ILoginView {
    @Inject
    lateinit var presenter: ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getLoginComponent().inject(this)
    }
}