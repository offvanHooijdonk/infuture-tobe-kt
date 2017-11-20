package com.prediction.tobe.data.auth

import android.content.Context
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FireBaseAuthUtil @Inject constructor() {
    /*@Inject
    lateinit var db: DatabaseReference*/
    @Inject
    lateinit var ctx: Context
    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    @Inject
    lateinit var authClientBuilder: GoogleApiClient.Builder

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    fun getAuthApiClientBuilder() = authClientBuilder

}