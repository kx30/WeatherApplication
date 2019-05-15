package com.example.weather

import android.Manifest
import android.os.Build
import android.os.Bundle
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
    }

    fun getPermission() {
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

    override fun setCurrentTemperature(weatherInfo: WeatherInfo) {
        currentTemperature.text = weatherInfo.temperatureCelsius
        Picasso.get()
            .load("http://${weatherInfo.currentCondition.iconUrl}")
            .into(currentWeatherCondition)
    }

    companion object {
        private val TAG = "WeatherActivity"
        var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    }

}
