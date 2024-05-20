package com.example.project.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern

@RequiresApi(Build.VERSION_CODES.O)
fun DateComparer(checkDate: String, startDate: String, endDate: String): Boolean {
    val start=startDate.slice(0..9)
    val end=endDate.slice(0..9)

    val formatter = ofPattern("yyyy-MM-dd")
    val checkDateFormatted = LocalDate.parse(checkDate, formatter)
    val startDateFormatted = LocalDate.parse(start, formatter)
    val endDateFormatted = LocalDate.parse(end, formatter)

    return (checkDateFormatted.isAfter(startDateFormatted) && checkDateFormatted.isBefore(endDateFormatted)) || start==checkDate || end==checkDate
}