package com.example.project


import android.app.Application
import com.example.project.data.local.LocalPreference


/**
 * prefs를 시스템 변수로 설정
 */
class MyApplication : Application() {
    companion object {
        lateinit var prefs: LocalPreference
    }

    override fun onCreate() {
        super.onCreate()
        prefs = LocalPreference(applicationContext)
    }
}