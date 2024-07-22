package com.artemklymenko.myweatherapp.ui.screens.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.artemklymenko.myweatherapp.domain.model.WeatherModel


@Composable
fun WeatherCard(
    weatherModel: WeatherModel,
    searchCity: () -> Unit,
    refreshData: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = weatherModel.lastUpdateTime,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp
                )
                AsyncImage(
                    modifier = Modifier.size(40.dp),
                    model = "https:${weatherModel.icon}",
                    contentDescription = "Weather condition"
                )
            }
            Text(
                text = weatherModel.city,
                fontFamily = FontFamily.SansSerif,
                fontSize = 32.sp
            )
            Text(
                text = "${weatherModel.currentTempC}°C",
                fontFamily = FontFamily.SansSerif,
                fontSize = 56.sp
            )
            Text(
                text = weatherModel.condition,
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    searchCity.invoke()
                }) {
                    Image(imageVector = Icons.Outlined.Search, contentDescription = "Search city")
                }
                IconButton(onClick = {
                    refreshData.invoke()
                }) {
                    Image(imageVector = Icons.Outlined.Refresh, contentDescription = "Refresh data")
                }
            }
        }
    }
}