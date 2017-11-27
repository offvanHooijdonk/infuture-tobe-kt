package com.prediction.tobe.presentation.presenter

import com.prediction.tobe.domain.PredictBean
import com.prediction.tobe.presentation.ui.predict.edit.IPredictEditView

interface IPredictEditPresenter {
    fun attachView(view: IPredictEditView)
    fun onSubmitForm(predictBean: PredictBean)
}