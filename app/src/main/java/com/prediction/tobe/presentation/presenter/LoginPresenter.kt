package com.prediction.tobe.presentation.presenter

import com.google.firebase.auth.FirebaseUser
import com.prediction.tobe.data.interactor.AuthInteractor
import com.prediction.tobe.presentation.ui.ILoginView
import javax.inject.Inject

class LoginPresenter @Inject constructor() : ILoginPresenter {

    @Inject
    lateinit var interactor: AuthInteractor

    lateinit var view: ILoginView

    override fun onViewCreated() {
        val user: FirebaseUser? = interactor.getCurrentUser()
        if (user != null) {
            // TODO
        } else {
            view.showAuthOptions(true)
        }
    }

    override fun attachView(view: ILoginView) {
        this.view = view
    }
}
