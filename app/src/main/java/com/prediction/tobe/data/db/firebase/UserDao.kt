package com.prediction.tobe.data.db.firebase

import com.google.firebase.database.*
import com.prediction.tobe.data.db.IUserDao
import com.prediction.tobe.domain.model.UserModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class UserDao @Inject constructor() : IUserDao {
    companion object {
        private const val NODE_NAME_USERS = "users"
    }

    private val userDB: DatabaseReference

    init {
        userDB = FirebaseDatabase.getInstance().getReference(NODE_NAME_USERS)
    }

    /**
     * @throws UserNotFoundException into <code>Observable</code>
     */
    override fun getUserById(userId: String): Observable<UserModel> {
        val subjectUser: PublishSubject<UserModel> = PublishSubject.create()

        userDB.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val userFound: UserModel? = dataSnapshot.getValue(UserModel::class.java)
                    if (userFound != null) {
                        subjectUser.onNext(userFound)
                    }
                } else {
                    subjectUser.onError(UserNotFoundException("User Data Snapshot does not exist!"))
                }
            }

            override fun onCancelled(dbErr: DatabaseError) {
                subjectUser.onError(dbErr.toException().apply { message?.plus("Error getting User Info")})
            }
        })
        return subjectUser
    }

    override fun updateUserInDB(userModel: UserModel): Observable<UserModel> {
        val subjectUser: PublishSubject<UserModel> = PublishSubject.create()
        userDB.child(userModel.id).setValue(userModel).addOnCompleteListener {
            subjectUser.onNext(userModel)
        }
        return subjectUser
    }
}