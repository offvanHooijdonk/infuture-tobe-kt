package com.prediction.tobe.domain.dto

import com.prediction.tobe.domain.model.PredictModel

data class PredictDto(var predictModel: PredictModel, var likesNumber: Long) {

    override fun equals(other: Any?): Boolean {
        return other is PredictDto && other.predictModel.equals(this.predictModel)
    }

    override fun hashCode(): Int {
        return predictModel.hashCode()
    }
}