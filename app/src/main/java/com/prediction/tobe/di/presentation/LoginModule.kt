package com.prediction.tobe.di.presentation

import com.prediction.tobe.presentation.presenter.ILoginPresenter
import com.prediction.tobe.presentation.presenter.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    @LoginScope
    fun provideLoginPresenter(): ILoginPresenter /*LoginPresenter*/ = LoginPresenter()
}