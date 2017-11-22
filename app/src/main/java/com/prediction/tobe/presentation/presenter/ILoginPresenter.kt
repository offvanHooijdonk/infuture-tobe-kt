package com.prediction.tobe.presentation.presenter

import android.content.Intent
import com.prediction.tobe.presentation.ui.ILoginView

interface ILoginPresenter {
    fun onViewCreated()
    fun attachView(view: ILoginView)
    fun onGoogleAuthSelected()
    fun onGoogleAuthReturn(data: Intent?)
}