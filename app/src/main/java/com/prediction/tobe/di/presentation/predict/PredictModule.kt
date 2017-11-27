package com.prediction.tobe.di.presentation.predict

import com.prediction.tobe.data.interactor.PredictInteractor
import com.prediction.tobe.presentation.presenter.PredictEditPresenter
import dagger.Module
import dagger.Provides

@Module
class PredictModule {
    @PredictScope
    @Provides
    fun providePredictPresenter() = PredictEditPresenter()

    @PredictScope
    @Provides
    fun providePredictInteractor() = PredictInteractor()
}