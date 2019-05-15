package com.example.weather

import com.arellomobile.mvp.MvpView
import com.example.weather.models.WeatherInfo

interface WeatherView: MvpView{
    fun setCurrentTemperature(weatherInfo: WeatherInfo)
}
