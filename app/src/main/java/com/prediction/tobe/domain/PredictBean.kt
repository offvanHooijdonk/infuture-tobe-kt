package com.prediction.tobe.domain

import java.util.*

data class PredictBean(val id: Int?,
                       val text: String,
                       val dateWhen: Long,
                       val likeCount: Long,
                       val ownerAnswer: Answer,
                       val likedByUser: Boolean) {

    override fun equals(other: Any?): Boolean =
            other is PredictBean && other.id == id

    override fun hashCode(): Int = id ?: Objects.hashCode(text)
}

enum class Answer {
    YES, NO
}