package com.developers.dranzer.data.models

data class WeatherData(
    val feelsLike: Double,
    val humidity: Int,
    val windForce: Double,
    val pressure: Int,
    val temperature: Double,
    val weatherDescription: String,
    val place: String
)