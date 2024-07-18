package com.artemklymenko.myweatherapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemklymenko.myweatherapp.ui.screens.sections.TabLayout
import com.artemklymenko.myweatherapp.ui.screens.sections.WeatherCard

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun WeatherScreen() {
    val weatherViewModel: WeatherViewModel = hiltViewModel()
    val weatherData = remember { weatherViewModel.allWeatherData }
    val currentWeatherData = remember { weatherViewModel.currentWeatherData }
    weatherViewModel.fetchWeather("London")
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            WeatherCard(currentWeatherData)
            TabLayout(weatherData.value, currentWeatherData)
        }
    }
}