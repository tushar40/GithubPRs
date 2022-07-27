package com.martnear.githubclosedprs.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeManager {
    private val dateTimeFormatter by lazy {
        SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.ENGLISH)
    }

    private val epochFormatter by lazy {
        SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.ENGLISH)
    }

    private fun getDateString(timestamp: Long): String? {
        return dateTimeFormatter.format(Date(timestamp))
    }

    private fun getTimeStamp(timeString: String): Long? {
        return epochFormatter.parse(timeString)?.time
    }

    fun getFormattedTime(timeString: String): String? {
        val timestamp = getTimeStamp(timeString)
        timestamp?.let {
            return getDateString(it)
        }
        return null
    }
}