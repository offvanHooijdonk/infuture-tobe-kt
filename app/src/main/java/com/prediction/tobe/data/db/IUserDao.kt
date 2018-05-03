package com.prediction.tobe.data.db

import com.prediction.tobe.domain.model.UserModel
import rx.Observable

interface IUserDao {
    fun getUserById(userId: String): Observable<UserModel>

    fun updateUserInDB(userModel: UserModel): Observable<UserModel>
}