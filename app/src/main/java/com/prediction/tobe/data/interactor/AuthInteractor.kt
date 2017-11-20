package com.prediction.tobe.data.interactor

import com.google.firebase.auth.FirebaseUser
import com.prediction.tobe.data.auth.FireBaseAuthUtil
import javax.inject.Inject

class AuthInteractor @Inject constructor() {
    @Inject
    lateinit var authUtil: FireBaseAuthUtil

    fun getCurrentUser(): FirebaseUser? = authUtil.getCurrentUser()

    fun getAuthApiClientBuilder() = authUtil.getAuthApiClientBuilder()
}