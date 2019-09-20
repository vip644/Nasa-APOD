package com.vipin.nasaapod.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vipin.nasaapod.model.Apod
import io.reactivex.Observable
import org.joda.time.LocalDate

/**
 * Created by vipin.c on 17/09/2019
 */

@Dao
interface ApodDao {

    @Insert
    fun addApod(apod: Apod)

    @Query("SELECT * FROM apod ORDER BY date DESC")
    fun getApodList(): LiveData<List<Apod>>

    @Query("SELECT * FROM apod WHERE date = :date")
    fun getApod(date: LocalDate): Apod

}