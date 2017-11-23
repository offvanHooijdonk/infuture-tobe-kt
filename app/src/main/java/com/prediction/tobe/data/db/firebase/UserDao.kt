package com.prediction.tobe.data.db.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.prediction.tobe.di.DependencyManager
import com.prediction.tobe.domain.UserBean
import rx.Observable
import rx.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Named

class UserDao @Inject constructor() : IUserDao {

   @field:[Inject Named(DependencyManager.DB_USERS)]
    lateinit var userDB: DatabaseReference

    override fun getUserById(userId: String): Observable<UserBean> {
        val subjectUser: PublishSubject<UserBean> = PublishSubject.create()

        userDB.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                if (dataSnapshot != null && dataSnapshot.exists()) {
                    val userFound: UserBean? = dataSnapshot.getValue(UserBean::class.java)
                    if (userFound != null) {
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

    override fun updateUserInDB(userBean: UserBean): Observable<UserBean> {
        val subjectUser: PublishSubject<UserBean> = PublishSubject.create()
        userDB.child(userBean.id).setValue(userBean).addOnCompleteListener {
            subjectUser.onNext(userBean)
        }
        return subjectUser
    }
}