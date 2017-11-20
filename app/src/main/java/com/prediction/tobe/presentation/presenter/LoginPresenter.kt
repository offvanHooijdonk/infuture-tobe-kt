package com.prediction.tobe.presentation.presenter

import com.prediction.tobe.data.interactor.AuthInteractor
import com.prediction.tobe.presentation.ui.ILoginView
import javax.inject.Inject

class LoginPresenter @Inject constructor() : ILoginPresenter {

    @Inject
    lateinit var interactor: AuthInteractor

    lateinit var view: ILoginView

    override fun onViewCreated() {
        view.proceedAsLogged()
    }

    override fun attachView(view: ILoginView) {
        this.view = view
        interactor.go()
    }
}
