package com.prediction.tobe.data.interactor

import com.prediction.tobe.data.db.firebase.PredictDao
import com.prediction.tobe.domain.dto.PredictDto
import com.prediction.tobe.domain.model.PredictModel
import com.prediction.tobe.helper.rx.schedulers
import io.reactivex.Observable
import javax.inject.Inject

class PredictInteractor @Inject constructor() {

    @Inject
    lateinit var predictDao: PredictDao

    fun loadLatestPredicts(): Observable<List<PredictDto>> = predictDao.getLatest().compose(schedulers<List<PredictDto>>())

    fun savePredict(predictModel: PredictModel): Observable<PredictModel> =
            if (predictModel.id == null) {
                predictDao.addPredict(predictModel)
            } else {
                predictDao.addPredict(predictModel) //  TODO call saveModel here
            }.compose(schedulers<PredictModel>())

}