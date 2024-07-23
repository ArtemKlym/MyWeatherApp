package com.artemklymenko.myweatherapp.ui.screens

import androidx.lifecycle.ViewModel
import com.artemklymenko.myweatherapp.datastore.StoreUserCity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val storeUserCity: StoreUserCity
) : ViewModel() {

    val city = storeUserCity.getCity

    suspend fun saveCity(city: String) {
        storeUserCity.saveCity(city)
    }
}