package com.example.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitClient.instance
        val jsonApi = retrofit.create(Api::class.java)
        val compositeDisposable = CompositeDisposable()

        val disposable = jsonApi.getCurrentWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ currentLocation ->
                Log.d(TAG, "onCreate: $currentLocation")
            }, {
                it.printStackTrace()
            })
        compositeDisposable.addAll(disposable)
    }
}
