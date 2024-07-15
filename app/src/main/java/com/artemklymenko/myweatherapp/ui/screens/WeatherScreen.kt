package com.artemklymenko.myweatherapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.artemklymenko.myweatherapp.ui.screens.sections.TabLayout
import com.artemklymenko.myweatherapp.ui.screens.sections.WeatherCard

@Preview(showBackground = true)
@Composable
fun WeatherScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        WeatherCard()
        TabLayout()
    }
}