package com.prediction.tobe.data.converter

import com.google.firebase.auth.FirebaseUser
import com.prediction.tobe.domain.UserBean

fun toUserBean(fu: FirebaseUser) = UserBean(fu.uid, fu.displayName, fu.photoUrl?.toString(), fu.email)
