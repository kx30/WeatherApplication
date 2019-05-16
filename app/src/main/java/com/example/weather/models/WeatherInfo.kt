package com.example.weather.models

import com.google.gson.annotations.SerializedName

data class WeatherInfo (
    @SerializedName("temp_c") val temperatureCelsius: String,
    @SerializedName("condition") val currentCondition: CurrentCondition,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("wind_kph") val windSpeed: String,
    @SerializedName("feelslike_c") val feelsLikeTemperatureCelsius: String,
    val uv: String,
    @SerializedName("pressure_mb") val pressureMb: String

)
