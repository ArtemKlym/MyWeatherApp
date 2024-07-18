package com.artemklymenko.myweatherapp.ui.screens.sections

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.artemklymenko.myweatherapp.domain.model.WeatherModel

@Composable
fun WeatherListItem(weatherItem: WeatherModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 2.dp, end = 2.dp)
            .clip(RoundedCornerShape(8.dp)),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = weatherItem.lastUpdateTime)
                Text(text = weatherItem.condition)
            }
            Text(
                text = weatherItem.currentTempC.ifEmpty {
                    "${weatherItem.minTempC}/${weatherItem.maxTempC} °C"
                },
                fontSize = 16.sp
            )
            AsyncImage(
                modifier = Modifier.size(32.dp),
                model = "https:${weatherItem.icon}",
                contentDescription = "Weather condition"
            )
        }
    }
}