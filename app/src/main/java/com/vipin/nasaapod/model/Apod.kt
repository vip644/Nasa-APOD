package com.vipin.nasaapod.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.joda.time.LocalDate

/**
 * Created by vipin.c on 17/09/2019
 */

@Entity
data class Apod(

    @PrimaryKey
    @SerializedName("date")
    var date: LocalDate,

    @SerializedName("title")
    var title: String?,

    @SerializedName("url")
    var url: String?,

    @SerializedName("hdurl")
    var hdUrl: String?,

    @SerializedName("explanation")
    var explanation: String?

)