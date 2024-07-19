package com.artemklymenko.myweatherapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemklymenko.myweatherapp.ui.screens.sections.TabLayout
import com.artemklymenko.myweatherapp.ui.screens.sections.WeatherCard
import com.artemklymenko.utils.MyAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun WeatherScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var city by remember { mutableStateOf("") }
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
            WeatherCard(
                currentWeatherData,
                searchCity = {
                    showDialog = true
                },
                refreshData = {
                    weatherViewModel.fetchWeather("London")
                }
            )
            TabLayout(weatherData.value, currentWeatherData)
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
                    showDialog = false
                    weatherViewModel.fetchWeather(city)
                }
            )
        }
    }
}