package com.prediction.tobe

import android.app.Application
import com.prediction.tobe.di.DependencyManager

class AppToBe : Application() {

    companion object {
        @JvmStatic lateinit var di: DependencyManager
    }
    override fun onCreate() {
        super.onCreate()

        di = DependencyManager(this)
    }


}
//fun getLoginComponent() = AppToBe.graph.plusAuthComponent(AuthModule()).plusLoginComponent(LoginModule())