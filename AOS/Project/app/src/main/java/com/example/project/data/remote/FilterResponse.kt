package com.example.project.data.remote

data class FilterResponse (
    var code: Int ,
    var message: String ,
    var data: List<Data>?
)
{
    data class Data(
        var filterId: Int,
        var filterName: String,
        var filterColor: String
    )
}
