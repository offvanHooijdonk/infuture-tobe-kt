package com.prediction.tobe.domain.model

data class UserModel(val id: String, val name: String?, val photoUrl: String?, val email: String?) {
    constructor() : this("", null, null, null)

    override fun equals(other: Any?): Boolean = other is UserModel && other.id == id

    override fun hashCode(): Int = id.hashCode()
}