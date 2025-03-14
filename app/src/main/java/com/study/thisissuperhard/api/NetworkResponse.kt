package com.study.thisissuperhard.api

// use T instead of WeatherModel so that we can use it everywhere
sealed class NetworkResponse<out T> {
//    important commit ammr-march
    data class Success<out T>(val data : T) : NetworkResponse<T>()
    data class Error(val message : String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}