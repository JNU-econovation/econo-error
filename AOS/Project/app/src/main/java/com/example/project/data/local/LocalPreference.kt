package com.example.project.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.project.data.entity.Event
import com.himanshoe.kalendar.KalendarEvent

class LocalPreference(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("ACCESS_TOKEN", Context.MODE_PRIVATE)

    fun getAToken(): String = prefs.getString("ACCESS_TOKEN", "").toString()

    fun setAToken(token: String) {
        prefs.edit().putString("ACCESS_TOKEN", token).apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return prefs.getBoolean(key, defValue)
    }

    fun setBoolean(key: String, bool: Boolean) {
        prefs.edit().putBoolean(key, bool).apply()
    }

    fun getCode(): String = prefs.getString("CODE", "").toString()
    fun setCode(code: String) {
        prefs.edit().putString("CODE", code).apply()
    }
}
data class UiState(
    val code: String = "",
    )