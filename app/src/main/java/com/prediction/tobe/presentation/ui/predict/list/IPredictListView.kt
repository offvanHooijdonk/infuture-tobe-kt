package com.prediction.tobe.presentation.ui.predict.list

import com.prediction.tobe.domain.dto.PredictDto

interface IPredictListView {
    fun onPredictsLoaded(predicts: List<PredictDto>)

    fun onLoadError(th: Throwable)
}