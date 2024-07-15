package com.example.project.ui.home

fun TimeModifier(dateTime: String): String {
    if(dateTime.length <= 15) return "날짜 정보 없음"
    val date= dateTime.slice(0..9)
    val hour = dateTime.slice(11..12)
    val minute = dateTime.slice(14..15)

    return if(minute == "00") "$date  $hour"+"시"
    else "$date  $hour"+"시 $minute"+"분"
}