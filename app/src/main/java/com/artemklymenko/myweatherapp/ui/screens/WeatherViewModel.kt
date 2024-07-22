package com.artemklymenko.myweatherapp.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemklymenko.myweatherapp.domain.WeatherRepository
import com.artemklymenko.myweatherapp.domain.model.WeatherModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _allWeatherData = mutableStateOf<List<WeatherModel>>(emptyList())
    val allWeatherData: State<List<WeatherModel>> get()  = _allWeatherData

    private val _currentWeatherData = mutableStateOf(
        WeatherModel("","","","","","","","")
    )
    val currentWeatherData: State<WeatherModel?> get()  = _currentWeatherData

    fun fetchWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getData(city, _allWeatherData, _currentWeatherData)
        }
    }
}