package com.developers.dranzer.data

import com.developers.dranzer.data.models.CurrentWeatherData
import com.developers.dranzer.data.network.WeatherApi
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.runCatching

class WeatherRepository(private val weatherApi: WeatherApi) {

    suspend fun getCurrentWeather(latitude: Double, longitude: Double): Result<CurrentWeatherData, Throwable> {
        return runCatching { weatherApi.getCurrentWeather(latitude, longitude) }
    }
}