package com.example.project.data.remote

data class LoginResponse (
    var code: Int ,
    var message: String ,
    var data: Data?
)
{
    data class Data(
        var accessToken: String,
        var accessExpiredTime: Long
    )
}
