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
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Search
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

@Composable
fun WeatherCard() {
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
                    text = "12 June",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp
                )
                AsyncImage(
                    modifier = Modifier.size(40.dp),
                    model = "https://cdn.weatherapi.com/weather/64x64/day/113.png",
                    contentDescription = "Weather condition"
                )
            }
            Text(
                text = "London",
                fontFamily = FontFamily.SansSerif,
                fontSize = 32.sp
            )
            Text(
                text = "30 *c",
                fontFamily = FontFamily.SansSerif,
                fontSize = 56.sp
            )
            Text(
                text = "sunny",
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(imageVector = Icons.Outlined.Search, contentDescription = "Search city")
                Image(imageVector = Icons.Outlined.Add, contentDescription = "")
            }
        }
    }
}