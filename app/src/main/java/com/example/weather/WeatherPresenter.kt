package com.example.weather

import android.content.Context
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.weather.WeatherActivity.Companion.permissions
import com.example.weather.models.LocationInfo
import com.example.weather.models.WeatherInfo
import com.example.weather.models.utils.PermissionManager
import com.example.weather.retrofit.Api
import com.example.weather.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class WeatherPresenter(context: Context) : MvpPresenter<WeatherView>() {

    private lateinit var locationInfo: LocationInfo
    private lateinit var weatherInfo: WeatherInfo

    private val TAG = "WeatherPresenter"


    init {
        PermissionManager.checkPermission(context, permissions)
        val city = GetterCurrentNameOfCity().getLocation(context)
        downloadData(city!!)
    }

    private fun downloadData(city: String) {
        val retrofit = RetrofitClient.instance
        val jsonApi = retrofit.create(Api::class.java)
        val compositeDisposable = CompositeDisposable()

        val disposable = jsonApi.getCurrentWeather(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ content ->
                locationInfo = content.location
                weatherInfo = content.weatherInfo
                Log.d(TAG, "downloadData: ${weatherInfo}")
                viewState.setCurrentTemperature(weatherInfo)
            }, {
                it.printStackTrace()
            })
        compositeDisposable.addAll(disposable)
    }
}