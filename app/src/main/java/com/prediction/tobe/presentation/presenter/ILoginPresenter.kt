package com.prediction.tobe.presentation.presenter

import com.prediction.tobe.presentation.ui.ILoginView

interface ILoginPresenter {
    fun onViewCreated()
    fun attachView(view: ILoginView)
}