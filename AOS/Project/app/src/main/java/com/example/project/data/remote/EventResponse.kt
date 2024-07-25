package com.example.project.data.remote

import com.example.project.data.entity.EventInfo

data class EventResponse (
    var code: String ,
    var message: String ,
    var data: EventInfo
)