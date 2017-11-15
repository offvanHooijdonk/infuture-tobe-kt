package com.prediction.tobe.presentation.presenter

import com.prediction.tobe.presentation.ui.ILoginView
import javax.inject.Inject

class LoginPresenter : ILoginPresenter {
    @Inject
    lateinit var view: ILoginView
}
