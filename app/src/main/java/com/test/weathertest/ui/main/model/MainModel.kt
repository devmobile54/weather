package com.test.weathertest.ui.main.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainModel(
    @SerializedName("temp")
    val temp: String = "",
    @SerializedName("pressure")
    val pressure: String = "",
    @SerializedName("humidity")
    val humidity: String = "",
    @SerializedName("temp_min")
    val temp_min: String = "",
    @SerializedName("temp_max")
    val temp_max: String = ""
) : Parcelable