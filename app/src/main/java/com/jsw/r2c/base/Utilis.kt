package com.jsw.r2c.base

import java.text.SimpleDateFormat
import java.util.Locale

object Utilis {
    fun convertDateTimeToString(datetime: String): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date = formatter.parse(datetime)
        val formattedDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return formattedDate.format(date)
    }
}