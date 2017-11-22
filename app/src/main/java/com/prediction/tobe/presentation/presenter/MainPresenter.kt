package com.prediction.tobe.presentation.presenter

import com.prediction.tobe.presentation.ui.IMainView
import javax.inject.Inject

class MainPresenter @Inject constructor() : IMainPresenter {
    lateinit var mainView: IMainView

    override fun attachView(mainView: IMainView) {
        this.mainView = mainView
    }
}