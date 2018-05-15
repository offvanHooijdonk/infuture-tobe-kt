package com.prediction.tobe.data.db.firebase

import com.google.firebase.database.*
import com.prediction.tobe.data.db.IPredictDao
import com.prediction.tobe.domain.dto.PredictDto
import com.prediction.tobe.domain.model.PredictModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class PredictDao : IPredictDao {

    companion object {
        private const val NODE_NAME_PREDICTS = "predicts"
    }

    private val predictDB: DatabaseReference

    init {
        predictDB = FirebaseDatabase.getInstance().getReference(NODE_NAME_PREDICTS)
    }

    override fun getLatest(): Observable<List<PredictDto>> {
        val subjPredicts: PublishSubject<List<PredictDto>> = PublishSubject.create()

        predictDB.limitToFirst(30).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val predicts: MutableList<PredictDto> = ArrayList()

                dataSnapshot.children.forEach {
                    val model: PredictModel? = it.getValue(PredictModel::class.java)
                    model?.id = it.key
                    val likesCount: Long = it.child("likes").childrenCount

                    if (model != null) predicts.add(PredictDto(model, likesCount))
                }

                subjPredicts.onNext(predicts)
                subjPredicts.onComplete()
            }

            override fun onCancelled(dbError: DatabaseError) {
                subjPredicts.onError(dbError.toException().apply { message?.plus("Error getting Latest Predicts.") })
            }
        })

        return subjPredicts;
    }

    override fun addPredict(predictModel: PredictModel): Observable<PredictModel> {
        val subj: PublishSubject<PredictModel> = PublishSubject.create()

        predictDB.push().setValue(predictModel) { dbError, dbRef ->
            if (dbError == null) {
                subj.onNext(predictModel)
                subj.onComplete()
            } else {
                subj.onError(dbError.toException().apply { message?.plus("Error saving Predict: " + predictModel.toString()) })
            }
        }

        return subj
    }
}