package com.test.weathertest.api

import com.test.weathertest.ui.main.model.WeatherModelResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/weather")
    fun getWeather(@Query("q") q: String, @Query("appid") appid: String): Single<Response<WeatherModelResponse>>
}