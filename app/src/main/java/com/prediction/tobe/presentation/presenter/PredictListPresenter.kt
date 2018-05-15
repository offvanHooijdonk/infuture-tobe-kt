package com.prediction.tobe.presentation.presenter

import com.prediction.tobe.data.interactor.PredictInteractor
import com.prediction.tobe.presentation.ui.predict.list.IPredictListView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class PredictListPresenter @Inject constructor() : IPredictListPresenter {
    @Inject
    protected lateinit var interactor: PredictInteractor

    private val compDisposable: CompositeDisposable = CompositeDisposable()

    private var view: IPredictListView? = null

    override fun attachView(view: IPredictListView) {
        this.view = view
    }

    override fun loadPredicts() {
        interactor.loadLatestPredicts()
                .subscribe({ view?.onPredictsLoaded(it) }, { view?.onLoadError(it) })
                .also { compDisposable.add(it) }
    }

    override fun detachView() {
        compDisposable.dispose()
        view = null
    }
}