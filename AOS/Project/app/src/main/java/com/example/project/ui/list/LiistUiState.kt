package com.example.project.ui.list

import com.example.project.data.entity.Event
import com.himanshoe.kalendar.KalendarEvent
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

sealed interface ListState {
    object LOADING : ListState
    object SUCCESS : ListState
    object ERROR : ListState
}
val instant = Clock.System.now()
val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
val todayString = "${localDateTime.year}-${localDateTime.monthNumber.toString().padStart(2, '0')}-${localDateTime.dayOfMonth.toString().padStart(2, '0')}"


data class LiistUiState(
    val state: ListState = ListState.LOADING,
    val day: String = "2024-04-16",
    val events: List<Event> = listOf<Event>(),

    val calendarEvent: List<KalendarEvent> = listOf<KalendarEvent>(),

//    val popupState: ListScreenState = ListScreenState.PopupShown
)

//sealed interface ListScreenState {
//    object Normal : ListScreenState
//    object PopupShown : ListScreenState
//}