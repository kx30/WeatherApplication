package com.example.weather

import com.arellomobile.mvp.MvpView

interface WeatherView: MvpView{
    fun setCurrentTemperature(temperature: Float)
//    fun requestPermissions()
}