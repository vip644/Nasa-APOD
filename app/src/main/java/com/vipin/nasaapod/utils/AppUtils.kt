package com.vipin.nasaapod.util

import android.text.TextUtils
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.vipin.nasaapod.R
import org.joda.time.LocalDate

/**
 * Created by vipin.c on 17/09/2019
 */
class DateRange(override val start: LocalDate, override val endInclusive: LocalDate) :
    ClosedRange<LocalDate>, Iterable<LocalDate> {

    override fun iterator(): Iterator<LocalDate> {
        return DateIterator(this)
    }
}


class DateIterator(private val dateRange: DateRange) : Iterator<LocalDate> {
    private var current: LocalDate = dateRange.start
    override fun next(): LocalDate {
        current = current.plusDays(1)
        return current
    }

    override fun hasNext(): Boolean {
        return current <= dateRange.endInclusive
    }
}

fun ImageView.loadImage(url: String){
    if (TextUtils.isEmpty(url)){
        Picasso.with(context).load(R.mipmap.ic_launcher_round)
    }else{
        Picasso.with(context).load(url).into(this)
    }
}

fun LocalDate.toJsonRequestFormat(): String = toString("yyyy-MM-dd")
