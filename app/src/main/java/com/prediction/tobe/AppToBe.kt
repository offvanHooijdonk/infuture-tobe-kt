package com.prediction.tobe

import android.app.Application
import com.prediction.tobe.di.AppComponent
import com.prediction.tobe.di.DaggerAppComponent

class AppToBe : Application() {

    companion object {
        @JvmStatic lateinit var graph: AppComponent
    }
    override fun onCreate() {
        super.onCreate()

        graph = DaggerAppComponent.builder().build()
    }


}
fun getLoginComponent() = AppToBe.graph.plusAuthComponent().plusLoginComponent()