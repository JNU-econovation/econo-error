package com.example.project.ui.filter

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.data.api.errorApi
import com.google.gson.Gson
import com.himanshoe.kalendar.KalendarEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import org.json.JSONException
import org.json.JSONObject

class FilterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FilterUiState())
    val uiState: StateFlow<FilterUiState> = _uiState.asStateFlow()


    private val _isFilterShown = mutableStateOf(false)
    val isFilterShown: State<Boolean> get() = _isFilterShown

//    fun loadFilters() {
//        // API 호출하여 데이터를 받아오는 로직
//        viewModelScope.launch {
//            val filters = renderFilter()
//            _uiState.value = uiState.value.copy(filterInfo = filters)
//        }
//    }


    fun renderFilter() {
        viewModelScope.launch {
            try {
                Log.d("홈오", "나옴")

                val response = errorApi.getFilters()
//
//                val jsonObject = Gson().toJson(response.body())
//                Log.d("번호", "1")
//                val jo = JSONObject(jsonObject.toString())
//                Log.d("번호", "2")
////                val responseJsonObject = jo.getJSONObject("data")
//                Log.d("번호", "3")
//                val filter = jo.getJSONObject("data")
//                Log.d("우와", "${response.body()}")
//                if (filter != null) {
//
//                    Log.d("월별 일정 리스트", "${filter}")
//
//                    val filterInfo = filterInfo(
//                        filterId = filter.getInt("filterId"),
//                        filterName = filter.getString("filterName"),
//                        filterColor = filter.getString("filterColor"),
//                        filterCategory = "PRIVATE"
//                    )
//
//
//                    _uiState.update { currentState ->
//                        currentState.copy(filterInfos = filterInfo)
//                    }
//                }


                val jsonObject = Gson().toJson(response.body())
                Log.d("번호", "1")
                val jo = JSONObject(jsonObject.toString())
                Log.d("번호", "2")
//                val responseJsonObject = jo.getJSONObject("data")
                Log.d("번호", "3")
                val filters = jo.getJSONArray("data")
                Log.d("우와", "${response.body()}")
                if (filters != null && filters.length() != 0) {
                    val uiFilterList: MutableList<filterInfo> = mutableListOf()
                    Log.d("월별 일정 리스트", "${uiFilterList}")

                    for (i in 0 until filters.length()) {
                        val filter = filters.optJSONObject(i)
                        Log.d("월별 일정 리스트", "${filter}")

                        val filterInfo = filterInfo(
                            filterId = filter.getInt("filterId"),
                            filterName = filter.getString("filterName"),
                            filterColor = filter.getString("filterColor"),
                            if (filter.getString("filterName") == "주간발표") {
                                "PUBLIC"
                            } else if (filter.getString("filterName") == "공식행사") {
                                "PUBLIC"
                            } else {
                                "PRIVATE"
                            }
                        )






                        uiFilterList.add(filterInfo)
                    }

                    _uiState.update { currentState ->
                        currentState.copy(filterInfos = uiFilterList)
                    }
                }


                } catch (e: JSONException) {
                    Log.d("월별 일정 리스트 오류", "${e}")
                    e.printStackTrace()
                }
            }



    }
}
