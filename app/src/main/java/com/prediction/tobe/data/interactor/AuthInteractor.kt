package com.prediction.tobe.data.interactor

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.prediction.tobe.data.auth.FireBaseAuthUtil
import com.prediction.tobe.data.db.firebase.UserNotFoundException
import com.prediction.tobe.di.DependencyManager
import com.prediction.tobe.domain.UserBean
import com.prediction.tobe.session.SessionHelper
import rx.Observable
import rx.subjects.PublishSubject
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named


class AuthInteractor @Inject constructor() {
    @Inject
    lateinit var authUtil: FireBaseAuthUtil

    @Inject
    lateinit var session: SessionHelper

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @field:[Inject Named(DependencyManager.DB_USERS)]
    lateinit var userDB: DatabaseReference

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    fun getSignInOptions(): GoogleSignInOptions = authUtil.getSignInOptions()

    fun signInWithGoogle(data: Intent): Observable<UserBean> {
        val result: GoogleSignInResult = authUtil.signInWithGoogleForResult(data)

        return if (result.isSuccess) {
            val account = result.signInAccount
            if (account != null) {
                authUtil.authWithGoogle(account)
                        .map { toUserBean(it) }
                        .flatMap { updateUserInDB(it) }
            } else {
                excAsObservable(Exception("Google account returned null despite successful Sign In"))
            }
        } else {
            excAsObservable(Exception(result.status.statusMessage))
        }
    }

    fun loadUserInfo(userBean: UserBean) : Observable<UserBean> = loadUserInfoById(userBean.id)

    fun loadUserInfo(userBean: FirebaseUser) : Observable<UserBean> = loadUserInfoById(userBean.uid)

    fun signOut() {
        firebaseAuth.signOut()
    }

    private fun loadUserInfoById(userId: String) : Observable<UserBean> {
        val subjectUser: PublishSubject<UserBean> = PublishSubject.create()

        userDB.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    val userFound: UserBean? = dataSnapshot.getValue(UserBean::class.java)
                    if (userFound != null) {
                        session.user = userFound
                        subjectUser.onNext(userFound)
                    }
                } else {
                    subjectUser.onError(UserNotFoundException("User Data Snapshot does not exist!"))
                }
            }
            override fun onCancelled(err: DatabaseError?) {
                subjectUser.onError(err?.toException())
            }
        })
        return subjectUser
    }

    // todo move this to a Model Converter
    private fun toUserBean(fu: FirebaseUser): UserBean =
            UserBean(fu.uid, fu.displayName, fu.photoUrl?.toString(), fu.email)

    private fun <T> excAsObservable(exception: Exception): Observable<T> =
            PublishSubject.error(exception)

    private fun updateUserInDB(userBean: UserBean): Observable<UserBean> {
        val subjectUser: PublishSubject<UserBean> = PublishSubject.create()
        userDB.child(userBean.id).setValue(userBean).addOnCompleteListener {
            session.user = userBean
            subjectUser.onNext(userBean)
        }
        return subjectUser
    }
}