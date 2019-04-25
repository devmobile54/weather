package com.test.weathertest.ui.basemodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiError(
    @SerializedName("cod")
    val statusCode: Int = 0,
    @SerializedName("message")
    val message: String = ""
) : Parcelable