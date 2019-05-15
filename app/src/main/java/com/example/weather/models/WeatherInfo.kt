package com.example.weather.models

import com.google.gson.annotations.SerializedName

data class WeatherInfo (
    @SerializedName("temp_c") val temperatureCelsius: String,
    @SerializedName("condition") val currentCondition: CurrentCondition
)
