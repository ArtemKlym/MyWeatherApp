package com.artemklymenko.myweatherapp.domain.model

data class WeatherModel(
    val city: String,
    val lastUpdateTime: String,
    val currentTempC:String,
    val condition: String,
    val icon: String,
    val maxTempC: String,
    val minTempC: String,
    val hours: String
)
