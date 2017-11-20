package com.prediction.tobe.presentation.ui

import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient

interface ILoginView {
    fun proceedAsLogged()
    fun showAuthOptions(show: Boolean)
    fun startGoogleAuthView(apiClient: GoogleApiClient)
    fun onConnectionFailed(connectionResult: ConnectionResult)
}