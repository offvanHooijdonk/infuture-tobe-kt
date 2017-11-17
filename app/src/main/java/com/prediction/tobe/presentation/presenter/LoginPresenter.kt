package com.prediction.tobe.presentation.presenter

import com.prediction.tobe.presentation.ui.ILoginView
import javax.inject.Inject

class LoginPresenter @Inject constructor() : ILoginPresenter {

    lateinit var view: ILoginView

    override fun onViewCreated() {
        view.proceedAsLogged()
    }

    override fun attachView(view: ILoginView) {
        this.view = view
    }
}
