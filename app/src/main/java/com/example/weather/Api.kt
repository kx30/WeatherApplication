package com.example.weather

import com.example.weather.models.JsonContent
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    companion object {
        const val  API_KEY = "7adc4eb4a8c14c448ef130643191305"
    }

    // current.json?key=7adc4eb4a8c14c448ef130643191305&q=Paris

    @GET("current.json?key=$API_KEY")
    fun getCurrentWeather(
        @Query("q") location: String = "Paris"
    ): Single<JsonContent>
}