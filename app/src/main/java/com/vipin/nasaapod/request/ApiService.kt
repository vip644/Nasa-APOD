package com.vipin.nasaapod.request

import com.vipin.nasaapod.model.Apod
import com.vipin.nasaapod.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by vipin.c on 16/09/2019
 */
interface ApiService {

    @GET("/planetary/apod")
    fun getApod(
        @Query("api_key") key: String?,
        @Query("date") date: String?,
        @Query("hd") hdImage: Boolean = false
    ): Single<Apod>
}