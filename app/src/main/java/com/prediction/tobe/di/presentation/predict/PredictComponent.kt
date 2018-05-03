package com.prediction.tobe.di.presentation.predict

import com.prediction.tobe.di.presentation.main.MainComponent
import com.prediction.tobe.di.presentation.main.MainModule
import com.prediction.tobe.presentation.ui.predict.edit.PredictEditActivity
import com.prediction.tobe.presentation.ui.predict.list.PredictListFragment
import dagger.Subcomponent

@Subcomponent(modules = [(PredictModule::class)])
@PredictScope
interface PredictComponent {
    fun inject(predictEditView: PredictEditActivity)
    fun inject(predictListView: PredictListFragment)

    fun plusMainComponent(mainModule: MainModule): MainComponent
}