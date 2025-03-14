@file:OptIn(ExperimentalLayoutApi::class)

package com.study.thisissuperhard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.study.thisissuperhard.api.NetworkResponse
import com.study.thisissuperhard.api.Weather
import com.study.thisissuperhard.api.WeatherRespModel
import com.study.thisissuperhard.ui.theme.ThisIsSuperHardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val weatherViewModel = WeatherViewModel()
        setContent {
            ThisIsSuperHardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainCompose(
                        weatherViewModel,
                        query = "penang",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainCompose(vm: WeatherViewModel, query : String, modifier: Modifier = Modifier) {
    var updated by rememberSaveable {
        mutableStateOf("")
    }

    var cityInput by rememberSaveable {
        mutableStateOf("")
    }
    val weatherResult = vm.weatherResult.observeAsState()
    val handleClick = {
        vm.getData(cityInput)
        updated = weatherResult.value.toString()
    }
    val onTextChange = {
        text:String -> cityInput = text
    }

    Column (
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Box(modifier = Modifier) {
            when(val result = weatherResult.value){

                is NetworkResponse.Error -> {
                    Log.i("ammar","error")
                    Text(text = result.message)
                    ErrorRow(message = result.message, Modifier)
                }
                NetworkResponse.Loading -> {
                    Log.i("ammar","loading")
                    Text(text = "Loading")
                }
                is NetworkResponse.Success -> {
                    Log.i("ammar","success")
                    SuccessRow(result.data.name, result.data.weather.first(), result.data.main)
                }
                null -> {
                    Text(text = " ")
                }
            }
        }
        TextField(value = cityInput, onValueChange = onTextChange)
        Button(onClick = handleClick) {
            Text(text = "get weather data")
        }
    }
    Fa()
}

@Composable
fun Fa() {
    var switchState by rememberSaveable {
        mutableStateOf(true)
    }

    val onSwitchChange = {
        value : Boolean -> switchState = value
    }

    Fb(switchState, onSwitchChange)

}

@Composable
fun Fb(switchState: Boolean, onSwitchChange: (Boolean)->Unit) {
    Switch(checked = switchState, onCheckedChange = onSwitchChange)
}

@Composable
fun ErrorRow(message: String, modifier: Modifier) {
    Box(){
        Text(
            text = "Error"
        )
    }
}

@Composable
fun SuccessRow(name:String, weather: Weather, main: Main) {
    Column (){
        Text(
            text = name
        )
        Text(
            text = weather.description
        )
        Text(
            text = main.temp.toString() + " | " + main.feels_like
        )

    }
}


@Preview(showBackground = true)
@Composable
fun ThisIsSuperHardPreview() {
    ThisIsSuperHardTheme {
        MainCompose(WeatherViewModel(), query = "penang", modifier = Modifier)
    }
}