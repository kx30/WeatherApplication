package com.example.weather

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.weather.models.utils.PermissionManager
import kotlinx.android.synthetic.main.activity_main.*


class WeatherActivity : MvpAppCompatActivity(), WeatherView {

    @InjectPresenter
    lateinit var weatherPresenter: WeatherPresenter

    companion object {
        private val TAG = "WeatherActivity"
    }

    private var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        PermissionManager.checkPermission(this, permissions)
//        CityManager().getLocation(this)

//        weatherPresenter.setContext(this)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (PermissionManager.checkPermission(this, permissions)) {
//                getLocation()
//            } else {
//                requestPermissions(permissions, PERMISSION_REQUEST)
//            }
//        } else {
//            getLocation()
//        }
    }

    // = WeatherPresenter(baseContext)

    @ProvidePresenter
    fun providePresenter() = WeatherPresenter(this)

    override fun setCurrentTemperature(temperature: Float) {
        currentTemperature.text = temperature.toString()
    }

//    override fun requestPermissions() {
//        ActivityCompat.requestPermissions(permissions, PERMISSION_REQUEST)
//    }

}
