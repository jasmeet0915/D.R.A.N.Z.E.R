package com.developers.dranzer.data.models

import com.fasterxml.jackson.annotation.JsonProperty

data class CurrentWeatherData(
    @JsonProperty("coord") val coord: Coord,
    @JsonProperty("weather") val weather: List<Weather>,
    @JsonProperty("base") val base: String,
    @JsonProperty("main") val main: Main,
    @JsonProperty("visibility") val visibility: Int,
    @JsonProperty("wind") val wind: Wind,
    @JsonProperty("clouds") val clouds: Clouds,
    @JsonProperty("dt") val dt: Int,
    @JsonProperty("sys") val sys: Sys,
    @JsonProperty("timezone") val timezone: Int,
    @JsonProperty("id") val id: Int,
    @JsonProperty("name") val name: String,
    @JsonProperty("cod") val cod: Int
)

data class Wind(
    @JsonProperty("speed") val speed: Double,
    @JsonProperty("deg") val deg: Int,
    @JsonProperty("gust") val gust: Double
)

data class Weather(
    @JsonProperty("id") val id: Int,
    @JsonProperty("main") val main: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("icon") val icon: String
)

data class Main(
    @JsonProperty("temp") val temp: Double,
    @JsonProperty("feels_like") val feelsLike: Double,
    @JsonProperty("temp_min") val tempMin: Double,
    @JsonProperty("temp_max") val tempMax: Double,
    @JsonProperty("pressure") val pressure: Int,
    @JsonProperty("humidity") val humidity: Int,
    @JsonProperty("sea_level") val seaLevel: Int,
    @JsonProperty("grnd_level") val grndLevel: Int
)


data class Sys(
    @JsonProperty("type") val type: Int,
    @JsonProperty("id") val id: Int,
    @JsonProperty("country") val country: String,
    @JsonProperty("sunrise") val sunrise: Int,
    @JsonProperty("sunset") val sunset: Int
)

data class Clouds(
    @JsonProperty("all") val all: Int
)

data class Coord(
    @JsonProperty("lon") val lon: Double,
    @JsonProperty("lat") val lat: Double
)

