package com.prediction.tobe.di.presentation

import com.prediction.tobe.presentation.presenter.ILoginPresenter
import com.prediction.tobe.presentation.presenter.LoginPresenter
import dagger.Module

@Module
class LoginModule {
    @LoginScope
    fun provideLoginPresenter(): ILoginPresenter = LoginPresenter()
}