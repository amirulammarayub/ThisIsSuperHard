package com.study.thisissuperhard.api

import com.study.thisissuperhard.ui.theme.WeatherApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiRetrofit {
    private const val BASE_URL  = "https://api.openweathermap.org"
//    /data/2.5/weather
    private fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val weatherApiService : WeatherApiService = getInstance().create(WeatherApiService::class.java)
}