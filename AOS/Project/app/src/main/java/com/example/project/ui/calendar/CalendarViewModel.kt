package com.example.project.ui.calendar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalendarViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(CalendarUiState())
    val uiState: StateFlow<CalendarUiState> = _uiState.asStateFlow()

    fun updateDay(day: String) {
        _uiState.value = uiState.value.copy(day = day)
    }
    fun updateRange(range: CalendarUiState.Range){
        _uiState.value = uiState.value.copy(range=range)
    }

}