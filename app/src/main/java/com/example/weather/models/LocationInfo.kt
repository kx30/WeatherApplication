package com.example.weather.models

data class LocationInfo (
    val name: String,
    val region: String,
    val country: String,
    val lat: Float,
    val lon: Float,
    val timeZoneId: String,
    val localTimeEpoch: String,
    val localTime: String
)
