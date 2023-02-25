package com.developers.dranzer.data.network

import com.developers.dranzer.data.models.CurrentWeatherData

interface WeatherApi {

    suspend fun getCurrentWeather(latitude: Float, longitude: Float): CurrentWeatherData
}