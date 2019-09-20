package com.vipin.nasaapod.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vipin.c on 17/09/2019
 */

@Module
class AppModule(application: Application) {

    val mApplication = application

    @Provides
    @Singleton
    fun providesApplication(): Application{
        return mApplication
    }
}