package com.vipin.nasaapod.repository

import androidx.lifecycle.LiveData
import com.vipin.nasaapod.database.ApodDatabase
import com.vipin.nasaapod.model.Apod
import com.vipin.nasaapod.request.ApiService
import com.vipin.nasaapod.util.DateRange
import com.vipin.nasaapod.utils.Constants
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.joda.time.LocalDate
import java.util.*

/**
 * Created by vipin.c on 17/09/2019
 */
class ApodRepoImpl(val apodDatabase: ApodDatabase, val apiService: ApiService) : ApodRepo {


    private fun addApod(apod: Apod) {
        apodDatabase.daoApod().addApod(apod)
    }

    override fun getApod(date: LocalDate): Single<Optional<Apod>> {

        return getApodFromDB(date).flatMap {
            if (it.isPresent) {
                Single.just(it)
            } else {
                getApodFromNetwork(date)
            }
        }
    }

    override fun getApodList(dateRange: DateRange): LiveData<List<Apod>> {

        Observable.just(dateRange)
            .subscribeOn(Schedulers.io())
            .flatMap {
                Observable.fromIterable(it)
            }
            .map { getApod(it).subscribe() }
            .subscribe()
        return getApodList()
    }

    override fun getApodList(): LiveData<List<Apod>> {
        return apodDatabase.daoApod().getApodList()
    }

    private fun getApodFromDB(date: LocalDate): Single<Optional<Apod>> {

        return Observable.fromCallable { Optional.ofNullable(apodDatabase.daoApod().getApod(date)) }
            .subscribeOn(Schedulers.io())
            .single(Optional.empty())

    }

    private fun getApodFromNetwork(date: LocalDate): Single<Optional<Apod>> {

        return apiService.getApod(
            Constants
                .KEY, date.toString("yyyy-MM-dd", Locale.getDefault())
        ).map {

            Optional.ofNullable(it)
        }.onErrorResumeNext {
            Single.just(Optional.empty())
        }.doOnSuccess {
            if (it.isPresent) addApod(it.get())
        }
    }


}