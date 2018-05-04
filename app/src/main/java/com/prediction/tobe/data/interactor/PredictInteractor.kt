package com.prediction.tobe.data.interactor

import com.prediction.tobe.data.db.firebase.PredictDao
import com.prediction.tobe.domain.dto.PredictDto
import com.prediction.tobe.domain.model.PredictModel
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class PredictInteractor @Inject constructor() {

    @Inject
    lateinit var predictDao: PredictDao

    fun loadLatestPredicts(): Observable<List<PredictDto>> = predictDao.getLatest()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun savePredict(predictModel: PredictModel): Observable<Void> =
            if (predictModel.id == null) {
                predictDao.addPredict(predictModel)
            } else {
                predictDao.addPredict(predictModel) //  TODO call saveModel here
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())


}