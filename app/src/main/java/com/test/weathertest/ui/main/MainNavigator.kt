package com.test.weathertest.ui.main

import com.test.weathertest.ui.base.BaseNavigator
import com.test.weathertest.ui.main.model.WeatherModelResponse

interface MainNavigator: BaseNavigator {

    fun getWeatherSuccess(t: WeatherModelResponse)
    fun getWeatherError(message: String)
}