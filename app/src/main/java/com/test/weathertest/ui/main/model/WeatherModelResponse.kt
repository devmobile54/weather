package com.test.weathertest.ui.main.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherModelResponse(
    @SerializedName("weather")
    val weatherModel: ArrayList<WeatherModel> = ArrayList(),
    @SerializedName("main")
    val mainModel: MainModel = MainModel(),
    @SerializedName("sys")
    val sysModel: SysModel = SysModel(),
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("cod")
    val cod: String = ""
) : Parcelable