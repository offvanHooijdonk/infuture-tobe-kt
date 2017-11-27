package com.prediction.tobe.presentation.presenter

import com.prediction.tobe.data.interactor.PredictInteractor
import com.prediction.tobe.domain.PredictBean
import com.prediction.tobe.presentation.ui.predict.edit.IPredictEditView
import javax.inject.Inject

class PredictEditPresenter @Inject constructor() : IPredictEditPresenter {
    @Inject
    lateinit var interactor: PredictInteractor

    private lateinit var view: IPredictEditView

    override fun attachView(view: IPredictEditView) {
        this.view = view
    }

    override fun onSubmitForm(predictBean: PredictBean) {

    }
}