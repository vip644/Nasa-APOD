package com.vipin.nasaapod.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vipin.nasaapod.ApodApplication
import com.vipin.nasaapod.di.components.ApodComponent

/**
 * Created by vipin.c on 17/09/2019
 */
class ApodVMFactory(val application: ApodApplication): ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val t: T = super.create(modelClass)
        if (t is ApodComponent.Injectable) {
            t.inject(application.apodComponent)
        }
        return t
    }
}