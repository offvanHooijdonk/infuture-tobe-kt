package com.prediction.tobe.data.converter

import com.google.firebase.auth.FirebaseUser
import com.prediction.tobe.domain.model.UserModel

fun toUserBean(fu: FirebaseUser) = UserModel(fu.uid, fu.displayName, fu.photoUrl?.toString(), fu.email)
