package com.artemklymenko.myweatherapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemklymenko.myweatherapp.ui.screens.sections.TabLayout
import com.artemklymenko.myweatherapp.ui.screens.sections.WeatherCard
import com.artemklymenko.utils.MyAlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun WeatherScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var city by remember { mutableStateOf("") }
    val weatherViewModel: WeatherViewModel = hiltViewModel()
    val weatherData = remember { weatherViewModel.allWeatherData }
    val currentWeatherData = remember { weatherViewModel.currentWeatherData }
    val cityViewModel: CityViewModel = hiltViewModel()
    val dataStore by cityViewModel.city.collectAsState(initial = null)

    LaunchedEffect(dataStore) {
        val cityToFetch = dataStore ?: "London"
        delay(500)
        weatherViewModel.fetchWeather(cityToFetch)
    }
    Scaffold {
        if (weatherData.value.isEmpty() || currentWeatherData.value == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                WeatherCard(
                    currentWeatherData.value!!,
                    searchCity = {
                        showDialog = true
                    }
                ) {
                    dataStore?.let { nonNullDataStore ->
                        weatherViewModel.fetchWeather(nonNullDataStore)
                    }
                }
                TabLayout(weatherData.value, currentWeatherData.value!!)
            }
            if (showDialog) {
                MyAlertDialog(
                    title = "Search city",
                    content = {
                        OutlinedTextField(
                            value = city,
                            onValueChange = { text ->
                                city = text
                            },
                            placeholder = {
                                Text(text = "London")
                            }
                        )
                    },
                    onCancel = { showDialog = false },
                    onConfirm = {
                        CoroutineScope(Dispatchers.IO).launch {
                            cityViewModel.saveCity(city)
                        }
                        showDialog = false
                        weatherViewModel.fetchWeather(city)
                    }
                )
            }
        }
    }
}