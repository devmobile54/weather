package com.test.weathertest.ui.main

import android.arch.lifecycle.MutableLiveData
import com.test.weathertest.api.ApiHelper
import com.test.weathertest.repositories.Repositories
import com.test.weathertest.ui.base.BaseViewModel
import com.test.weathertest.ui.basemodel.ApiError
import com.test.weathertest.ui.main.model.WeatherModelResponse
import io.reactivex.disposables.Disposable

class MainViewModel(var navigator: MainNavigator): BaseViewModel<MainNavigator>() {
    private val repositories =  Repositories()
    var weatherModel: MutableLiveData<WeatherModelResponse>? = MutableLiveData()

    fun loadWeather(city: String, appId: String){
        repositories.getWeather(city, appId, GetWeather())
    }

    internal inner class GetWeather : ApiHelper.OnApiCallbackListener<WeatherModelResponse> {

        override fun onSubscribe(d: Disposable) { }

        override fun onSuccess(t: WeatherModelResponse) {
            weatherModel?.value = t
        }

        override fun onError(apiError: ApiError) {
            navigator.getWeatherError(apiError.message)
        }
    }
}