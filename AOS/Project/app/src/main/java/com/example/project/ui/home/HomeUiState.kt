package com.example.project.ui.home

import com.example.project.data.entity.Event
import com.example.project.data.entity.EventInfo
import com.example.project.ui.list.todayString

sealed interface HomeState {
    object LOADING : HomeState
    object SUCCESS : HomeState
    object ERROR : HomeState
}

data class HomeUiState(
    val state: HomeState = HomeState.LOADING,
    val events: List<Event> = listOf<Event>(),
    val day: String = "$todayString",

    val name: String = "",
    val place: String = "",
    val info:String = "",
    val startTime:String= "",
    val endTime:String="",

    val eventInfo: EventInfo = EventInfo( 0,"","","","",""),

    val int: Int = 0,
    val createPopupState: CreateState = CreateState.PopupShown,


    val eventPopupState: EventState = EventState.Normal


)

sealed interface CreateState {
    object Normal : CreateState
    object PopupShown : CreateState
}

sealed interface EventState {
    object Normal : EventState
    object PopupShown : EventState
}
