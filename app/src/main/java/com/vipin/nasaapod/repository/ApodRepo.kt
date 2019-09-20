package com.vipin.nasaapod.repository

import androidx.lifecycle.LiveData
import com.vipin.nasaapod.model.Apod
import com.vipin.nasaapod.util.DateRange
import io.reactivex.Single
import org.joda.time.LocalDate
import java.util.*

/**
 * Created by vipin.c on 17/09/2019
 */
interface ApodRepo {

    fun getApod(date: LocalDate): Single<Optional<Apod>>
    fun getApodList(dateRange: DateRange): LiveData<List<Apod>>
    fun getApodList(): LiveData<List<Apod>>
}