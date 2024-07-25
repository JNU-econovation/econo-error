package com.example.project.data.remote

data class CommonResponse (
    var code: Int ,
    var message: String ,
    var data: Data?
)
{
    data class Data(
        var eventId: Int
    )
}
