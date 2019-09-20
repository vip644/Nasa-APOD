package com.vipin.nasaapod.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vipin.nasaapod.model.Apod

/**
 * Created by vipin.c on 17/09/2019
 */

@Database(entities = [Apod::class], version = 1, exportSchema = false)
@TypeConverters(ApodDateConverter::class)
abstract class ApodDatabase : RoomDatabase() {

    abstract fun daoApod(): ApodDao
}