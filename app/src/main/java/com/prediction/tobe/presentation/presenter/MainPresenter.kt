package com.prediction.tobe.presentation.presenter

import com.google.firebase.auth.FirebaseAuth
import com.prediction.tobe.presentation.ui.main.IMainView
import javax.inject.Inject

class MainPresenter @Inject constructor() : IMainPresenter {
    lateinit var view: IMainView
    @Inject
    lateinit var auth: FirebaseAuth

    override fun attachView(mainView: IMainView) {
        this.view = mainView
    }

    override fun logOut() {
        auth.signOut()
    }

    override fun onViewInit() {

    }
}