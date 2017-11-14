package com.prediction.tobe.domain

data class UserBean(val id: String, val name: String, val photoUrl: String, val email: String) {
    override fun equals(other: Any?): Boolean {
        return other is UserBean && other.id == id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}