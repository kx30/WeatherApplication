package com.example.weather.models

import com.google.gson.annotations.SerializedName

data class CurrentCondition (
    @SerializedName("icon") val iconUrl: String
)
