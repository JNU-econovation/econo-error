package com.example.project.data.remote

import com.example.project.data.entity.YearEvent

data class YearEventResponse (
    var code: String ,
    var message: String ,
    var data: Event
)
{
    data class Event(
        var events: List<YearEvent>
    )
}