package com.test.weathertest.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class AppConstants {

    companion object {

        @SuppressLint("SimpleDateFormat")
        fun getDateTime(): String {
            val tz = TimeZone.getTimeZone("UTC +7")
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            inputFormat.timeZone = tz

            val calendar = Calendar.getInstance(tz)
            val format = SimpleDateFormat("dd MMM yyyy HH:mm:ss:aa")

            return format.format(calendar.time)
        }

        fun convertKelvinToCelsius(kelvin: Float): String{
            return Math.round(kelvin - 273.15F).toString()
        }

        fun convertKelvinToFahrenheit(kelvin: Float): String{
            return Math.round((kelvin - 273.15F) * 9/5 + 32).toString()
        }
    }
}