package com.prediction.tobe.data.db.firebase

import com.prediction.tobe.domain.UserBean
import rx.Observable

interface IUserDao {
    fun getUserById(userId: String): Observable<UserBean>

    fun updateUserInDB(userBean: UserBean): Observable<UserBean>
}