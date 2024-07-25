package com.example.project.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.data.api.errorApi
import com.example.project.data.entity.EventInfo
import com.example.project.data.remote.EventRequest
import com.example.project.ui.filter.FilterViewModel
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun updateDay(day: String) {
        _uiState.value = uiState.value.copy(day = day)
    }



    fun updateName(name: String){
        _uiState.value = uiState.value.copy(name=name)
    }
    fun updatePlace(place: String){
        _uiState.value = uiState.value.copy(place=place)
    }
    fun updateInfo(info: String){
        _uiState.value = uiState.value.copy(info=info)
    }
//    fun updateStartTime(startTime: String){
//        _uiState.value = uiState.value.copy(startHour = startTime)
//    }
//    fun updateEndTime(endTime: String){
//        _uiState.value=uiState.value.copy(endHour =endTime)
//    }

    fun updateScope(scope: String){
        _uiState.value = uiState.value.copy(scope=scope)
    }


    fun createTogglePopup() {
        _uiState.value = if (uiState.value.createPopupState == CreateState.Normal) {
            HomeUiState(createPopupState = CreateState.PopupShown)
        } else {
            HomeUiState(createPopupState = CreateState.Normal)
        }
    }

    fun popFilter() {
        _uiState.value = if (uiState.value.filterState == FilterState.Normal) {
            HomeUiState(filterState = FilterState.BSShown)
        } else {
            HomeUiState(filterState = FilterState.Normal)
        }
    }

    fun eventTogglePopup() {

        _uiState.value = if (uiState.value.eventPopupState == EventState.Normal) {
            HomeUiState(eventPopupState = EventState.PopupShown)
        } else {
            HomeUiState(eventPopupState = EventState.Normal)
        }
    }

    fun createEvent(eventRequest: EventRequest) {
        viewModelScope.launch {
            try {
                Log.d("등록", "scope 시작")

                val response = errorApi.createEvent(eventRequest)
                Log.d("월별 일정 get 상태코드", "${response}")
                when (response.code()) {
                    200 -> {
                        Log.d("월별 일정 get 상태코드", "${response.code()}")
                    }
                }
//
                val jsonObject = Gson().toJson(response.body())
                val jo = JSONObject(jsonObject.toString())
                val responseJsonObject = jo.getJSONObject("data")
                val events = responseJsonObject.getJSONArray("events")
Log.d("일정 생성 완료", " 1")
                createTogglePopup()
            } catch (e: JSONException) {
                Log.d("월별 일정 리스트 오류", "${e}")
                e.printStackTrace()
            }
        }
    }

    fun updateEvent(eventRequest: EventRequest) {
        viewModelScope.launch {
            try {
                Log.d("등록", "scope 시작")

                val response = errorApi.updateEvent(1, eventRequest)
                Log.d("월별 일정 get 상태코드", "${response}")
                when (response.code()) {
                    200 -> {
                        Log.d("월별 일정 get 상태코드", "${response.code()}")
                    }
                }
//
                val jsonObject = Gson().toJson(response.body())
                val jo = JSONObject(jsonObject.toString())
                val responseJsonObject = jo.getJSONObject("data")
                val events = responseJsonObject.getJSONArray("events")
                Log.d("일정 생성 완료", " 1")
                createTogglePopup()
            } catch (e: JSONException) {
                Log.d("월별 일정 리스트 오류", "${e}")
                e.printStackTrace()
            }
        }
    }

    fun deleteEvent(eventId: Int) {
        viewModelScope.launch {
            try {
                Log.d("등록", "scope 시작")

                val response = errorApi.deleteEvent(eventId)
                Log.d("월별 일정 get 상태코드", "${response}")
                when (response.code()) {
                    200 -> {
                        Log.d("월별 일정 get 상태코드", "${response.code()}")
                    }
                }
//
                val jsonObject = Gson().toJson(response.body())
                val jo = JSONObject(jsonObject.toString())
                val responseJsonObject = jo.getJSONObject("data")
                val events = responseJsonObject.getJSONArray("events")
                Log.d("일정 생성 완료", " 1")
                createTogglePopup()
            } catch (e: JSONException) {
                Log.d("월별 일정 리스트 오류", "${e}")
                e.printStackTrace()
            }
        }
    }

    fun renderEventInfo(eventId: Int) {
        viewModelScope.launch {
            try {
                Log.d("홈오", "나옴")

                val response = errorApi.getEvent(eventId)
//
                val jsonObject = Gson().toJson(response.body())
                Log.d("번호", "1")
                val jo = JSONObject(jsonObject.toString())
                Log.d("번호", "2")
//                val responseJsonObject = jo.getJSONObject("data")
                Log.d("번호", "3")
                val event = jo.getJSONObject("data")
                Log.d("우와", "${response.body()}")
                if (event != null) {

                        Log.d("월별 일정 리스트", "${event}")

                        val eventInfo = EventInfo(
                            eventId= event.getInt("eventId"),
                            eventName = event.getString("eventName"),
                            eventEndDate = event.getString("eventEndDate"),
                            eventStartDate = event.getString("eventStartDate"),
                            eventInfo = event.getString("eventInfo"),
                            eventPlace = event.getString("eventPlace"),
                        )


                    _uiState.update { currentState ->
                        currentState.copy(eventInfo = eventInfo)
                    }
                }

            } catch (e: JSONException) {
                Log.d("월별 일정 리스트 오류", "${e}")
                e.printStackTrace()
            }


        }


    }
}

