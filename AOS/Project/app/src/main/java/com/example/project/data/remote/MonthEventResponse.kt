package com.example.project.data.remote

import com.example.project.data.entity.Event

data class MonthEventResponse (
    var code: String ,
    var message: String ,
    var data: List<Event>
)
//{
//    data class Data(
//        var events: List<Event>
//    )
//}