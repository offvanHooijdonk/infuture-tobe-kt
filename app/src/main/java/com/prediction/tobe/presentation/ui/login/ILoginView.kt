package com.prediction.tobe.presentation.ui.login

import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient

interface ILoginView {
    fun navigateToMain()
    fun showAuthOptions(show: Boolean)
    fun startGoogleAuthView(apiClient: GoogleApiClient)
    fun onConnectionFailed(connectionResult: ConnectionResult)
    fun showAuthProgress(show: Boolean)
    fun showUserNotFound()
}