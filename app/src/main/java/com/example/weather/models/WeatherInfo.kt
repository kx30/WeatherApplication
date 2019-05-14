package com.example.weather.models

import com.google.gson.annotations.SerializedName

data class WeatherInfo (
    @SerializedName("temp_c") val temperatureCelsius: Float
//    val lastUpdatedEpoch: String,
//    val lastUpdated: String,
//    val temperatureCelsius: Float,
//    val temperatureFahrenheit: Float,
//    val isDay: Byte,
//    val currentCondition: CurrentCondition,
//    val windMph: Float,
//    val windKph: Float,
//    val windDegree: Int,
//    val windDirectory: String,
//    val pressureMb: Float,
//    val pressureIn: Float,
//    val precipMm: Float,
//    val precipIn: Float,
//    val humidity: Int,
//    val cloud: Float,
//    val feelsLikeCelsius: Float,
//    val feelsLikeFahrenheit: Float,
//    val visKm: Float,
//    val visMiles: Float,
//    val uv: Float,
//    val gustMph: Float,
//    val gustKph: Float
)
