package com.artemklymenko.myweatherapp.data

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.artemklymenko.utils.Constants.API_KEY
import com.artemklymenko.myweatherapp.domain.WeatherRepository
import com.artemklymenko.myweatherapp.domain.model.WeatherModel
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(private val context: Context) : WeatherRepository {

    override fun getData(
        city: String,
        dayList: MutableState<List<WeatherModel>>,
        currentDay: MutableState<WeatherModel>
    ) {
        val url = buildUrl(city)
        val queue = Volley.newRequestQueue(context)
        val request = createRequest(url, dayList, currentDay)
        queue.add(request)
    }

    private fun buildUrl(city: String): String {
        return "https://api.weatherapi.com/v1/forecast.json?key=$API_KEY&q=$city&days=5&aqi=no&alerts=no"
    }

    private fun createRequest(
        url: String,
        dayList: MutableState<List<WeatherModel>>,
        currentDay: MutableState<WeatherModel>
    ): StringRequest {
        return StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val list = parseWeatherResponse(response)
                currentDay.value = list[0]
                dayList.value = list
            },
            { error ->
                Log.e("WeatherRepositoryImpl", "Volley Error: $error")
            }
        )
    }

    private fun parseWeatherResponse(response: String): List<WeatherModel> {
        if (response.isEmpty()) return emptyList()

        val mainObject = JSONObject(response)
        val city = mainObject.getJSONObject("location").getString("name")
        val days = mainObject.getJSONObject("forecast").getJSONArray("forecastday")

        val list = ArrayList<WeatherModel>()
        for (i in 0 until days.length()) {
            val item = days.getJSONObject(i)
            list.add(
                WeatherModel(
                    city = city,
                    lastUpdateTime = item.getString("date"),
                    currentTempC = "",
                    condition = item.getJSONObject("day").getJSONObject("condition")
                        .getString("text"),
                    icon = item.getJSONObject("day").getJSONObject("condition").getString("icon"),
                    maxTempC = item.getJSONObject("day").getString("maxtemp_c"),
                    minTempC = item.getJSONObject("day").getString("mintemp_c"),
                    hours = item.getJSONArray("hour").toString()
                )
            )
        }

        val currentWeather = list[0].copy(
            currentTempC = mainObject.getJSONObject("current").getString("temp_c"),
            lastUpdateTime = mainObject.getJSONObject("current").getString("last_updated")
        )
        list[0] = currentWeather

        return list
    }
}