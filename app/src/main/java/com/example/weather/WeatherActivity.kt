package com.example.weather

import android.Manifest
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.weather.models.LocationInfo
import com.example.weather.models.WeatherInfo
import com.example.weather.models.utils.PermissionManager
import com.example.weather.retrofit.Api
import com.example.weather.retrofit.RetrofitClient
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class WeatherActivity : MvpAppCompatActivity(), WeatherView {

    @InjectPresenter
    lateinit var weatherPresenter: WeatherPresenter

    @ProvidePresenter
    fun providePresenter() = WeatherPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        getPermission()
//        downloadData()
//        PermissionManager.checkPermission(this, permissions)
//        GetterCurrentNameOfCity().getLocation(this)
        getPermission()

        initBackground()
    }

    private fun initBackground() {
        val animationDrawable = mainLinearLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

    private fun getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionManager.checkPermission(this, permissions)) {
                GetterCurrentNameOfCity().getLocation(this)
            } else {
                requestPermissions(permissions, 10)
            }
        } else {
            GetterCurrentNameOfCity().getLocation(this)
        }
    }

    override fun setCurrentWeatherInfo(weatherInfo: WeatherInfo) {
        currentTemperature.text = weatherInfo.temperatureCelsius
        currentWeatherConditionTextView.text = weatherInfo.currentCondition.currentConditionText
        Picasso.get()
            .load("http://${weatherInfo.currentCondition.iconUrl}")
            .into(currentWeatherConditionImageView)

        windDirectionTextView.text = weatherInfo.windDirection
        windSpeedTextView.text = "${weatherInfo.windSpeed}km/h"
        realFeelTemperature.text = "${weatherInfo.feelsLikeTemperatureCelsius}℃"
        uvIndexText.text = weatherInfo.uv
        currentPressureTextView.text = weatherInfo.pressureMb
    }

    companion object {
        private val TAG = "WeatherActivity"
        var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    }
}
