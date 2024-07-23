package com.artemklymenko.myweatherapp.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.artemklymenko.utils.Constants.USER_STORE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserCity(
    private val context: Context
) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(USER_STORE_NAME)
        val USER_CITY_KEY = stringPreferencesKey("user_city")
    }

    val getCity: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_CITY_KEY] ?: ""
        }

    suspend fun saveCity(city: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_CITY_KEY] = city
        }
    }
}