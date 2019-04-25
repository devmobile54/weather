package com.test.weathertest.ui.main.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SysModel(
    @SerializedName("type")
    val type: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("message")
    val message: String = "",
    @SerializedName("country")
    val country: String = "",
    @SerializedName("sunrise")
    val sunrise: String = "",
    @SerializedName("sunset")
    val sunset: String = ""
) : Parcelable