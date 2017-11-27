package com.prediction.tobe.di.presentation.predict

import com.prediction.tobe.di.presentation.main.MainComponent
import com.prediction.tobe.di.presentation.main.MainModule
import com.prediction.tobe.presentation.ui.predict.edit.PredictEditActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(PredictModule::class))
@PredictScope
interface PredictComponent {
    fun inject(predictEditView: PredictEditActivity)

    fun plusMainComponent(mainModule: MainModule): MainComponent
}