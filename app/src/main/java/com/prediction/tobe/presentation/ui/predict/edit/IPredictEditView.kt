package com.prediction.tobe.presentation.ui.predict.edit

interface IPredictEditView {

    fun onPredictSaved()
    fun onSaveError(throwable: Throwable)
}