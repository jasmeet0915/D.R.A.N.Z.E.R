package com.developers.dranzer.domain

import com.developers.dranzer.data.WeatherRepository
import com.developers.dranzer.data.models.WeatherData
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun invoke(latitude: Double, longitude: Double): Result<WeatherData, Throwable> {
        return when (val result = weatherRepository.getCurrentWeather(latitude, longitude)) {
            is Ok -> {
                val weatherData = result.value
                Ok(
                    WeatherData(
                        feelsLike = weatherData.main.feelsLike,
                        humidity = weatherData.main.humidity,
                        windForce = weatherData.wind.speed,
                        pressure = weatherData.main.pressure,
                        temperature = weatherData.main.temp,
                        weatherDescription = weatherData.weather.first().description,
                        place = weatherData.name
                    )
                )
            }
            is Err -> result
        }
    }
}