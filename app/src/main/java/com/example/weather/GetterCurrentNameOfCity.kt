package com.example.weather

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import java.util.*

class GetterCurrentNameOfCity {

    companion object {
        private val TAG = "GetterCurrentNameOfCity"
    }

    private lateinit var locationManager: LocationManager
    private var hasGps = false
    private var locationGps: Location? = null

    @SuppressLint("MissingPermission")
    fun getLocation(context: Context): String {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (hasGps) {
            Log.d(TAG, "getLocation: hasNetwork")
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0f, object :
                LocationListener {
                override fun onLocationChanged(location: Location?) {
                    if (location != null) {
                        locationGps = location
                        Log.d(
                            "CodeAndroidLocation",
                            "Gps latitude: ${locationGps!!.latitude}, Gps longitude: ${locationGps!!.longitude}"
                        )
                    }
                }
                override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}
                override fun onProviderEnabled(p0: String?) {}
                override fun onProviderDisabled(p0: String?) {}
            })

            val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (localGpsLocation != null) {
                locationGps = localGpsLocation
            }

            if (locationGps != null) {
                Log.d(
                    TAG,
                    "Gps latitude: ${locationGps!!.latitude}, Gps longitude: ${locationGps!!.longitude}"
                )
                return getCityName(context)
            }

        } else {
            context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
        return "Moscow"
    }

    private fun getCityName(context: Context): String {
        val geoCoder = Geocoder(context, Locale.getDefault())
        val address = geoCoder.getFromLocation(locationGps!!.latitude, locationGps!!.longitude, 1)
        return address[0].locality
    }
}