package com.example.project.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.data.api.errorApi
import com.example.project.data.entity.Event
import com.google.gson.Gson
import com.himanshoe.kalendar.KalendarEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import org.json.JSONException
import org.json.JSONObject

class ListViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LiistUiState())
    val uiState: StateFlow<LiistUiState> = _uiState.asStateFlow()


    fun updateList(events: List<Event>) {
        _uiState.value = LiistUiState(state = ListState.SUCCESS, events = events)
    }

//    fun togglePopup() {
//        _uiState.value = if (uiState.value.popupState == ListScreenState.Normal) {
//            LiistUiState(popupState = ListScreenState.PopupShown)
//        } else {
//            LiistUiState(popupState = ListScreenState.Normal)
//        }
//    }








    fun renderMonthEventList() {
        viewModelScope.launch {
            try {
                Log.d("홈오", "나옴")

                val instant = Clock.System.now()
                val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
                val todayString = "${localDateTime.year}-${localDateTime.monthNumber}-${localDateTime.dayOfMonth}"
                Log.d("월별 일정 get 상태코드", "${todayString}")
                val response = errorApi.getAllEvent(todayString)
                Log.d("월별 일정 get 상태코드", "${response}")
                when (response.code()) {
                    200 -> {
                        Log.d("월별 일정 get 상태코드", "${response.code()}")
                    }
                }
//
                val jsonObject = Gson().toJson(response.body())
                Log.d("번호", "1")
                val jo = JSONObject(jsonObject.toString())
                Log.d("번호", "2")
//                val responseJsonObject = jo.getJSONObject("data")
                Log.d("번호", "3")
                val events = jo.getJSONArray("data")
                Log.d("우와", "${response.body()}")
                if (events != null && events.length() != 0) {
                    val uiEventList: MutableList<Event> = mutableListOf()
                    val calendarEvents = mutableListOf<KalendarEvent>()
                    Log.d("월별 일정 리스트", "${uiEventList}")

                    for (i in 0 until events.length()) {
                        val event = events.optJSONObject(i)
                        Log.d("월별 일정 리스트", "${event}")

                        val eventInfo = Event(
                            eventId= event.getInt("eventId"),
                            eventName = event.getString("eventName"),
                            eventEndDate = event.getString("eventEndDate"),
                            eventStartDate = event.getString("eventStartDate")
                        )



                        var currentDate = LocalDate.parse(eventInfo.eventStartDate.take(10))
                        val endDate = LocalDate.parse(eventInfo.eventEndDate.take(10))
                        while (currentDate <= endDate) {
                            calendarEvents.add(KalendarEvent(currentDate, "${eventInfo.eventName}","세부" ))
                            currentDate = currentDate.plus(1, kotlinx.datetime.DateTimeUnit.DAY)
                        }



                        uiEventList.add(eventInfo)
                    }

                    _uiState.update { currentState ->
                        currentState.copy(calendarEvent = calendarEvents)
                    }


                    _uiState.update { currentState ->
                        currentState.copy(events = uiEventList)
                    }
                }

            } catch (e: JSONException) {
                Log.d("월별 일정 리스트 오류", "${e}")
                e.printStackTrace()
            }


        }


    }
}
