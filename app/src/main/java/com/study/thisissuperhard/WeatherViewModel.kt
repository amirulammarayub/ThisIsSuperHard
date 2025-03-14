package com.study.thisissuperhard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.thisissuperhard.api.NetworkResponse
import com.study.thisissuperhard.api.WeatherApiRetrofit
import com.study.thisissuperhard.api.WeatherRespModel
import com.study.thisissuperhard.ui.theme.Constant
import kotlinx.coroutines.launch

class WeatherViewModel :ViewModel() {
    private val weatherApiService = WeatherApiRetrofit.weatherApiService
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherRespModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherRespModel>> = _weatherResult
    fun getData (city : String) {

        viewModelScope.launch {
            _weatherResult.value = NetworkResponse.Loading
            val response = weatherApiService.getWeather(apikey = Constant.apiKey,city)
            try {
                if(response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            }
            catch (e:Exception) {
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }

        }

    }
}