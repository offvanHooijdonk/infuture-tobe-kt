package com.prediction.tobe.di.presentation.main

import com.prediction.tobe.presentation.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    @MainScope
    fun provideMainPresenter(): MainPresenter = MainPresenter()
}