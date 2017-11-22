package com.prediction.tobe.di.presentation.login

import com.prediction.tobe.data.interactor.AuthInteractor
import com.prediction.tobe.presentation.presenter.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    @LoginScope
    fun provideLoginPresenter(): LoginPresenter = LoginPresenter()

    @Provides
    @LoginScope
    fun provideAuthInteractor(): AuthInteractor = AuthInteractor()
}