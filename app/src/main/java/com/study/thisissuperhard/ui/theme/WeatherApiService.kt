package com.study.thisissuperhard.ui.theme

import com.study.thisissuperhard.api.WeatherRespModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("/data/2.5/weather")
    suspend fun getWeather(
        @Query("appid") apikey : String,
        @Query("q") city: String
    ) : Response<WeatherRespModel>
}