package com.example.project.ui.calendar

import com.example.project.ui.list.todayString
import com.himanshoe.kalendar.KalendarEvents

sealed interface CalendarState {
    object LOADING : CalendarState
    object SUCCESS : CalendarState
    object ERROR : CalendarState
}

data class CalendarUiState(
    val state: CalendarState = CalendarState.LOADING,
    val day: String = "$todayString",
    val range: Range= Range(),
    val events: List<KalendarEvents> = emptyList(),

    ){
data class Range(
        val start: String = "",
        val end: String = ""
    )
}
