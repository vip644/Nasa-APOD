package com.vipin.nasaapod.database

import androidx.room.TypeConverter
import org.joda.time.LocalDate

/**
 * Created by vipin.c on 17/09/2019
 */
class ApodDateConverter {

    @TypeConverter
    fun fromTimeStamp(value: Long?): LocalDate {
        return LocalDate(value)
    }

    @TypeConverter
    fun localDateToTimestamp(localDate: LocalDate): Long? {
        return localDate.toDateTimeAtStartOfDay().millis
    }
}