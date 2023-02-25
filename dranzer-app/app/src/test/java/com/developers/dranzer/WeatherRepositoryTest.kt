package com.developers.dranzer

import com.developers.dranzer.data.FakeWeatherApi
import com.developers.dranzer.data.WeatherRepository
import com.developers.dranzer.data.models.*
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Test

class WeatherRepositoryTest {

    @Test
    fun `it fetches weather successfully`() = runBlocking {
        // given
        val fakeWeatherApi = FakeWeatherApi(
            "./src/test/resources/weather_api_response.json"
        )

        // when
        val currentWeatherData = WeatherRepository(fakeWeatherApi).getCurrentWeather(
            latitude = 23.43f,
            longitude = 42.32f
        )

        // then
        val coord = Coord(77.292, 28.6849)
        val weather = Weather(800, "Clear", "clear sky", "01n")
        val main = Main(
            temp = 294.27,
            feelsLike = 293.01,
            tempMin = 294.27,
            tempMax = 294.27,
            pressure = 1017,
            humidity = 22,
            seaLevel = 1017,
            grndLevel = 992
        )
        val wind = Wind(
            speed = 1.13,
            deg = 249,
            gust = 1.6
        )
        val sys = Sys(
            type = 1,
            id = 9165,
            country = "IN",
            sunrise = 1677374391,
            sunset = 1677415690
        )
        assertThat(currentWeatherData).isEqualTo(
            Result.success(
                CurrentWeatherData(
                    coord = coord,
                    weather = listOf(weather),
                    base = "stations",
                    main = main,
                    visibility = 10000,
                    wind = wind,
                    clouds = Clouds(0),
                    dt = 1677352344,
                    sys = sys,
                    timezone = 19800,
                    id = 1273435,
                    name = "Darya Ganj",
                    cod = 200
                )
            )
        )
    }
}