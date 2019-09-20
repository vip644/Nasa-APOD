package com.vipin.nasaapod.di.modules

import com.vipin.nasaapod.database.ApodDatabase
import com.vipin.nasaapod.repository.ApodRepo
import com.vipin.nasaapod.repository.ApodRepoImpl
import com.vipin.nasaapod.request.ApiService
import dagger.Module
import dagger.Provides

/**
 * Created by vipin.c on 17/09/2019
 */

@Module
class RepoModule {

    @Provides
    fun provideApodRepo(apodDatabase: ApodDatabase, apiService: ApiService): ApodRepo {

        return ApodRepoImpl(apodDatabase, apiService)
    }
}