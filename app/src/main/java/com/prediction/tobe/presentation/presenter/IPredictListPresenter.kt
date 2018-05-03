package com.prediction.tobe.presentation.presenter

import com.prediction.tobe.presentation.ui.predict.list.IPredictListView

interface IPredictListPresenter {
    fun attachView(view: IPredictListView)
    fun detachView()
    fun loadPredicts()
}