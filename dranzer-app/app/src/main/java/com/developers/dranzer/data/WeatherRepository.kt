package com.developers.dranzer.data

import com.developers.dranzer.data.models.CurrentWeatherData
import com.developers.dranzer.data.network.WeatherApi

class WeatherRepository(private val weatherApi: WeatherApi) {

    suspend fun getCurrentWeather(latitude: Float, longitude: Float): Result<CurrentWeatherData> {
        return runCatching { weatherApi.getCurrentWeather(latitude, longitude) }
    }
}