package com.example.project.ui.login

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.MyApplication
import com.example.project.data.api.errorApi
import com.example.project.data.entity.Event
import com.example.project.data.local.LocalPreference
import com.example.project.data.local.UiState
import com.example.project.ui.list.LiistUiState
import com.example.project.ui.list.ListState
import com.example.project.ui.navigation.NavItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _uuiState = MutableStateFlow(UiState())
    val uuiState: StateFlow<UiState> = _uuiState.asStateFlow()

    fun updateCode(code: String) {
        _uuiState.value = _uuiState.value.copy(code = code)
        loginWithSlack(code)
    }

    fun loginWithSlack(code: String) {
        viewModelScope.launch {
            val uri= "https://econo-calendar.com/login"
            try {
                val response = errorApi.loginWithSlack(MyApplication.prefs.getCode(), uri )

                if (response.isSuccessful) {
                    val token = response.body()?.data?.accessToken
                    if (token != null) {
                        MyApplication.prefs.setAToken("Bearer $token")

//                        navController.navigate(NavItem.Home.screenRoute)
                    } else {
                        Log.d("로그인 에러", "로그인 헤더 없음")
                    }
                } else {
                    Log.d("로그인 에러", "${response.code()}")
                }
            } catch (e: Exception) {
                Log.d("로그인 에러", "${e.message}")
            }
        }
    }
}
