package com.vipin.nasaapod.di.modules

import android.app.Application
import androidx.room.Room
import com.vipin.nasaapod.database.ApodDao
import com.vipin.nasaapod.database.ApodDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vipin.c on 17/09/2019
 */

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): ApodDatabase{

        return Room.databaseBuilder(application, ApodDatabase::class.java, "apod_database").build()
    }

    @Provides
    @Singleton
    fun provideApodDao(apodDatabase: ApodDatabase): ApodDao{
        return apodDatabase.daoApod()
    }
}