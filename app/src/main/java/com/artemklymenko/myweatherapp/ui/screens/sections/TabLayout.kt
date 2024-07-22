package com.artemklymenko.myweatherapp.ui.screens.sections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.artemklymenko.myweatherapp.domain.model.TabItem
import com.artemklymenko.myweatherapp.domain.model.WeatherModel
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout(weatherData: List<WeatherModel>, currentWeatherData: WeatherModel) {
    val tabList = listOf(
        TabItem(title = "HOURS"),
        TabItem(title = "DAYS")
    )
    val pagerState = rememberPagerState { 2 }
    var selectedTabIndex by remember{
        mutableIntStateOf(0)
    }

    LaunchedEffect(selectedTabIndex){
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    val scope = rememberCoroutineScope()
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ) {
            tabList.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                        selectedTabIndex = index
                    },
                    text = {
                        Text(text = item.title)
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {index ->
            val list = if(index == 0){
                getWeatherByHours(currentWeatherData.hours)
            }else{
                weatherData
            }
           MainList(weatherData = list)
        }
    }
}

private fun getWeatherByHours(hours: String): List<WeatherModel> = if(hours.isEmpty()){
    listOf()
}else{
    val hoursArray = JSONArray(hours)
    val list = ArrayList<WeatherModel>()

    for(i in 0 until hoursArray.length()){
        val item = hoursArray[i] as JSONObject
        list.add(
            WeatherModel(
                city = "",
                lastUpdateTime = item.getString("time"),
                currentTempC = item.getString("temp_c")+"°C",
                condition = item.getJSONObject("condition").getString("text"),
                icon = item.getJSONObject("condition").getString("icon"),
                maxTempC = "",
                minTempC = "",
                hours = ""
            )
        )
    }
    list
}

@Composable
fun MainList(weatherData: List<WeatherModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        itemsIndexed(
            weatherData
        ){_, item ->
            WeatherListItem(item)
        }
    }
}