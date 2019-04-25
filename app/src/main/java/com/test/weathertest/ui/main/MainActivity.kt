package com.test.weathertest.ui.main

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.test.weathertest.R
import com.test.weathertest.interfaces.OnToolbarClickListener
import com.test.weathertest.ui.base.BaseActivity
import com.test.weathertest.ui.main.model.WeatherModelResponse
import com.test.weathertest.utils.AppConstants
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainNavigator, MainViewModel>(),
    MainNavigator,
    View.OnClickListener,
    OnToolbarClickListener{

    private lateinit var mViewModel: MainViewModel
    private var isCelsius = true

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = MainViewModel(this)

        setBindingData()
        subscribeData()
    }

    override fun setBindingData() {
        toolbar.setListener(this)
        btnSearch.setOnClickListener(this)
    }

    override fun subscribeData() {
        mViewModel.weatherModel?.observe(this, Observer {
            it?.also {
                it1 -> setData(it1)
                getWeatherSuccess(it1)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setData(weatherModel: WeatherModelResponse?) {
        weatherModel?.also {
            tvEmpty.text = ""
            tvCityName.text = "${weatherModel.name}, ${weatherModel.sysModel.country}"
            tvDate.text = "${getString(R.string.main_today)} ${AppConstants.getDateTime()}"
            tvDescription.text = weatherModel.weatherModel[0].description
            tvHumidity.text = "${getString(R.string.main_humidity)} ${weatherModel.mainModel.humidity} %"
            imvIcon.background = null
            if (isCelsius)
                tvTemperature.text = "${AppConstants.convertKelvinToCelsius(weatherModel.mainModel.temp.toFloat())} ${getString(R.string.main_celsius)}"
            else
                tvTemperature.text = "${AppConstants.convertKelvinToFahrenheit(weatherModel.mainModel.temp.toFloat())} ${getString(R.string.main_fahrenheit)}"
        }
    }

    override fun onToolbarClickListener(view: View) {
        when(view.id) {
            (R.id.celsius) -> {
                isCelsius = true
                mViewModel.weatherModel?.value?.also {
                    setData(it)
                }
            }
            (R.id.fahrenheit) -> {
                isCelsius = false
                mViewModel.weatherModel?.value?.also {
                    setData(it)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when(v){
            btnSearch -> {
                if (edtSearch.text.toString().isNotEmpty()){
                    showProgressDialog()
                    mViewModel.loadWeather(edtSearch.text.toString(), getString(R.string.app_id))
                }else{
                    Toast.makeText(this, getString(R.string.main_message_empty_search), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setDefault(){
        tvCityName.text = ""
        tvDate.text = ""
        tvTemperature.text = ""
        tvDescription.text = ""
        tvHumidity.text = ""
        imvIcon.background = null
    }

    override fun getWeatherSuccess(t: WeatherModelResponse) {
        dismissProgressDialog()
        Log.i("getWeatherSuccess", t.toString())
    }

    override fun getWeatherError(message: String) {
        setDefault()
        tvEmpty.text = message
        dismissProgressDialog()
        Log.i("getWeatherError", message)
    }
}
