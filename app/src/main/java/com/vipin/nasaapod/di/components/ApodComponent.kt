package com.vipin.nasaapod.di.components

import com.vipin.nasaapod.di.modules.AppModule
import com.vipin.nasaapod.di.modules.DatabaseModule
import com.vipin.nasaapod.di.modules.NetworkModule
import com.vipin.nasaapod.di.modules.RepoModule
import com.vipin.nasaapod.viewmodel.MultiGridViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by vipin.c on 17/09/2019
 */

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, NetworkModule::class, RepoModule::class])
interface ApodComponent {

    fun inject(multiGridViewModel: MultiGridViewModel)

    interface Injectable {

        fun inject(apodComponent: ApodComponent)
    }
}