package com.test.weathertest.repositories

import com.test.weathertest.api.ApiHelper
import com.test.weathertest.api.ApiService
import com.test.weathertest.api.ServiceGenerator
import com.test.weathertest.ui.main.model.WeatherModelResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repositories {

    private var apiHelper = ApiHelper()

    fun getWeather(city: String, appId: String, onApiCallbackListener: ApiHelper.OnApiCallbackListener<WeatherModelResponse>) {
        return ServiceGenerator.createService(ApiService::class.java).getWeather(city, appId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(apiHelper.CallBackWithGenericListener(onApiCallbackListener))
    }
}