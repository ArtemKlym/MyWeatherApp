package com.artemklymenko.myweatherapp.domain

import androidx.compose.runtime.MutableState
import com.artemklymenko.myweatherapp.domain.model.WeatherModel

interface WeatherRepository {
    fun getData(city: String, dayList: MutableState<List<WeatherModel>>, currentDay: MutableState<WeatherModel>)
}