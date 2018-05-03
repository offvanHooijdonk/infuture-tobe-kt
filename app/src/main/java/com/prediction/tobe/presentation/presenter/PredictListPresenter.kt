package com.prediction.tobe.presentation.presenter

import com.prediction.tobe.data.interactor.PredictInteractor
import com.prediction.tobe.presentation.ui.predict.list.IPredictListView
import javax.inject.Inject

class PredictListPresenter @Inject constructor() : IPredictListPresenter {
    @Inject
    protected lateinit var interactor: PredictInteractor

    private var view: IPredictListView? = null

    override fun attachView(view: IPredictListView) {
        this.view = view
    }

    override fun loadPredicts() {
        interactor.loadLatestPredicts()
                .subscribe({ view?.onPredictsLoaded(it) }, { view?.onLoadError(it) })
    }

    override fun detachView() {
        view = null
    }
}