package com.prediction.tobe.data.interactor

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.prediction.tobe.data.auth.FireBaseAuthUtil
import com.prediction.tobe.data.converter.toUserBean
import com.prediction.tobe.data.db.firebase.UserDao
import com.prediction.tobe.domain.model.UserModel
import com.prediction.tobe.session.SessionHelper
import rx.Observable
import rx.subjects.PublishSubject
import java.lang.Exception
import javax.inject.Inject

class AuthInteractor @Inject constructor() {
    @Inject
    lateinit var authUtil: FireBaseAuthUtil

    @Inject
    lateinit var session: SessionHelper

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var userDao: UserDao

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    fun getSignInOptions(): GoogleSignInOptions = authUtil.getSignInOptions()

    fun signInWithGoogle(data: Intent): Observable<UserModel> {
        val result: GoogleSignInResult = authUtil.signInWithGoogleForResult(data)

        return if (result.isSuccess) {
            val account = result.signInAccount
            if (account != null) {
                authUtil.authWithGoogle(account)
                        .map { toUserBean(it) }
                        .flatMap { userDao.updateUserInDB(it) }
                        .doOnNext { session.user = it }
            } else {
                excAsObservable(Exception("Google account returned null despite successful Sign In"))
            }
        } else {
            excAsObservable(Exception(result.status.statusMessage))
        }
    }

    fun loadUserInfo(userModel: UserModel) : Observable<UserModel> = loadUserInfoById(userModel.id)

    fun loadUserInfo(userBean: FirebaseUser) : Observable<UserModel> = loadUserInfoById(userBean.uid)

    fun signOut() {
        firebaseAuth.signOut()
    }

    private fun loadUserInfoById(userId: String) : Observable<UserModel> =
            userDao.getUserById(userId).doOnNext { session.user = it }

    private fun <T> excAsObservable(exception: Exception): Observable<T> =
            PublishSubject.error(exception)

}