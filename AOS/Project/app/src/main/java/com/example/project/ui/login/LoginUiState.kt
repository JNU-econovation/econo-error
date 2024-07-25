package com.example.project.ui.login

import com.example.project.data.entity.Event
import com.himanshoe.kalendar.KalendarEvent
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class LoginUiState(
    val day: String = "2024-04-16",
    val events: List<Event> = listOf<Event>(),

    val calendarEvent: List<KalendarEvent> = listOf<KalendarEvent>(),

    )