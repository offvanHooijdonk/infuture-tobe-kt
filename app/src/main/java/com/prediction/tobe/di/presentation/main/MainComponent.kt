package com.prediction.tobe.di.presentation.main

import com.prediction.tobe.presentation.ui.main.MainActivity
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}