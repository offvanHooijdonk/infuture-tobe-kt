package com.prediction.tobe.data.auth

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.prediction.tobe.R
import rx.Observable
import rx.subjects.PublishSubject
import java.lang.Exception
import javax.inject.Inject

class FireBaseAuthUtil @Inject constructor() {
    /*@Inject
    lateinit var db: DatabaseReference*/
    @Inject
    lateinit var ctx: Context
    @Inject
    lateinit var firebaseAuth: FirebaseAuth
    /*@Inject
    lateinit var authClientBuilder: GoogleApiClient.Builder*/

    fun getSignInOptions(): GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(ctx.getString(R.string.web_client_id))
            .requestEmail()
            .build()

    fun signInWithGoogleForResult(data: Intent): GoogleSignInResult =
            Auth.GoogleSignInApi.getSignInResultFromIntent(data)

    fun authWithGoogle(account: GoogleSignInAccount): Observable<FirebaseUser> {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        val subjectUser: PublishSubject<FirebaseUser> = PublishSubject.create()
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener({ task ->
                    if (task.isSuccessful) {
                        if (firebaseAuth.currentUser != null) {
                            subjectUser.onNext(firebaseAuth.currentUser)
                        } else {
                            subjectUser.onError(Exception("No user authorized despite successful Sign In!"))
                        }
                    } else {
                        subjectUser.onError(task.exception)
                    }
                })
        return subjectUser
    }

}