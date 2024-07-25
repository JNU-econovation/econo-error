package com.example.project.data.remote

data class EventRequest (
    val eventName: String,
    val eventStartDate: String,
    val eventEndDate: String,
    val eventInfo: String,
    val eventPlace: String,
    val scheduleType: String,
    val filter: filterId,
){
    data class filterId(
        val filterId: Int=12
    )
}