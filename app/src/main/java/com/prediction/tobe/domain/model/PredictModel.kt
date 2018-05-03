package com.prediction.tobe.domain.model

import java.util.*

data class PredictModel(var id: String?,
                        var text: String,
                        var dateWhen: Long,
                        var likeCount: Long,
                        var ownerAnswer: Answer,
                        var likedByUser: Boolean,
                        var active: Boolean = true) {
    constructor() : this(null, "", 0, 0, Answer.NO, false, false)

    override fun equals(other: Any?): Boolean =
            other is PredictModel && other.id == id

    override fun hashCode(): Int = Objects.hashCode(text)

    override fun toString(): String = Objects.toString(this)

    enum class Answer {
        YES, NO
    }
}

