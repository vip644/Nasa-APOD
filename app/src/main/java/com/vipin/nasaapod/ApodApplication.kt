package com.vipin.nasaapod

import android.app.Application
import com.vipin.nasaapod.di.components.ApodComponent
import com.vipin.nasaapod.di.components.DaggerApodComponent
import com.vipin.nasaapod.di.modules.AppModule

/**
 * Created by vipin.c on 17/09/2019
 */
class ApodApplication : Application() {

    val apodComponent: ApodComponent by lazy {
        DaggerApodComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}