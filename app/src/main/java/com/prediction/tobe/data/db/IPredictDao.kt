package com.prediction.tobe.data.db

import com.prediction.tobe.domain.dto.PredictDto
import com.prediction.tobe.domain.model.PredictModel
import io.reactivex.Observable

interface IPredictDao {
    fun getLatest(): Observable<List<PredictDto>>
    fun addPredict(predictModel: PredictModel):  Observable<PredictModel>
}