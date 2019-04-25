package com.test.weathertest.ui.main.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class WeatherModel(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("main")
    val main: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("icon")
    val icon: String = ""
) : Parcelable