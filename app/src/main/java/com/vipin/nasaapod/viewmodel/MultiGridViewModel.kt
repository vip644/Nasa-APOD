package com.vipin.nasaapod.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vipin.nasaapod.di.components.ApodComponent
import com.vipin.nasaapod.model.Apod
import com.vipin.nasaapod.repository.ApodRepo
import com.vipin.nasaapod.util.DateRange
import javax.inject.Inject

/**
 * Created by vipin.c on 17/09/2019
 */
class MultiGridViewModel : ViewModel(), ApodComponent.Injectable {

    @Inject
    lateinit var apodRepo: ApodRepo

    fun getApodList(dateRange: DateRange): LiveData<List<Apod>> {

        return apodRepo.getApodList(dateRange)
    }

    override fun inject(apodComponent: ApodComponent) {
        apodComponent.inject(this)
    }
}