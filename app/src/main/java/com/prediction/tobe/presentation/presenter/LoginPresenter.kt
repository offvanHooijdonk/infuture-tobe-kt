package com.prediction.tobe.presentation.presenter

import android.content.Context
import com.prediction.tobe.presentation.ui.ILoginView
import javax.inject.Inject

class LoginPresenter @Inject constructor() : ILoginPresenter {

    lateinit var view: ILoginView
    @Inject
    lateinit var ctx: Context

    override fun onViewCreated() {
        view.proceedAsLogged()
    }

    override fun attachView(view: ILoginView) {
        this.view = view
    }
}
