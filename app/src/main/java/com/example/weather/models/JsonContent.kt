package com.example.weather.models

import com.google.gson.annotations.SerializedName

data class JsonContent(
    val location: LocationInfo,
    @SerializedName("current") val weatherInfo: WeatherInfo
)
