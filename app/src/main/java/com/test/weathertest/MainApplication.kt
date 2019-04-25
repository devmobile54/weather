package com.test.weathertest

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MainApplication: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context : Context
        private var mainApplication: MainApplication? = null
        @Synchronized
        fun getInstance(): MainApplication {
            return mainApplication!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        mainApplication = this
        context = baseContext
    }
}