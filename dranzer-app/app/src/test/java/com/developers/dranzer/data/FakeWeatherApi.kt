package com.developers.dranzer.data

import com.developers.dranzer.data.models.CurrentWeatherData
import com.developers.dranzer.data.network.WeatherApi
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

class FakeWeatherApi(private val fakeDataSource: String): WeatherApi {

    override suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeatherData {
        val response = File(fakeDataSource).readBytes()
        return ObjectMapper().readValue(response, CurrentWeatherData::class.java)
    }
}