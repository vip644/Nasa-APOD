package com.vipin.nasaapod.request

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.vipin.nasaapod.model.Apod
import com.vipin.nasaapod.util.toJsonRequestFormat
import org.joda.time.LocalDate

/**
 * Created by vipin.c on 17/09/2019
 */
class ApodJsonAdapter {

    @FromJson
    internal fun apodFromJson(apodJson: ApodJson): Apod {
        // Convert YYYY-MM-DD to Date object
        val date: LocalDate = LocalDate.parse(apodJson.date)
        val apod = Apod(date, apodJson.title,
            apodJson.url, apodJson.explanation,
            apodJson.hdurl)
        return apod
    }

    @ToJson
    internal fun apodToJson(apod: Apod): ApodJson {
        return ApodJson(apod.date.toJsonRequestFormat(),
            apod.title!!, apod.url!!, apod.explanation!!)
    }
}