package com.prediction.tobe.presentation.presenter

import com.prediction.tobe.presentation.ui.main.IMainView

interface IMainPresenter {
    fun attachView(mainView: IMainView)
    fun logOut()
    fun onViewInit()
}