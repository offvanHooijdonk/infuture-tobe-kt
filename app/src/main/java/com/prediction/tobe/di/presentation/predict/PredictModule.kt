package com.prediction.tobe.di.presentation.predict

import com.prediction.tobe.data.interactor.PredictInteractor
import com.prediction.tobe.presentation.presenter.PredictEditPresenter
import com.prediction.tobe.presentation.presenter.PredictListPresenter
import dagger.Module
import dagger.Provides

@Module
class PredictModule {
    @PredictScope
    @Provides
    fun providePredictEditPresenter() = PredictEditPresenter()

    @PredictScope
    @Provides
    fun providePredictListPresenter() = PredictListPresenter()

    @PredictScope
    @Provides
    fun providePredictInteractor() = PredictInteractor()

}