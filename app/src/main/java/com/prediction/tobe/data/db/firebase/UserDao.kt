package com.prediction.tobe.data.db.firebase

import com.google.firebase.database.*
import com.prediction.tobe.domain.UserBean
import rx.Observable
import rx.subjects.PublishSubject
import javax.inject.Inject

class UserDao @Inject constructor() : IUserDao {
    companion object {
        private const val NODE_NAME_USERS = "users"
    }

    val userDB: DatabaseReference

    init {
        userDB = FirebaseDatabase.getInstance().getReference(NODE_NAME_USERS)
    }

    /**
     * @throws UserNotFoundException into <code>Observable</code>
     */
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