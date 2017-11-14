package com.prediction.tobe.domain

data class PredictBean(val id: Int, val text: String, val dateWhen: Long, val likeCount: Long, val likedByUser: Boolean) {

    override fun equals(other: Any?): Boolean {
        return other is PredictBean && other.id == id
    }

    override fun hashCode(): Int {
        return id
    }
}