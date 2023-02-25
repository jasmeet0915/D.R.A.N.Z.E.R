package com.developers.dranzer.data.network

import com.developers.dranzer.data.models.CurrentWeatherData
import okhttp3.OkHttpClient
import retrofit.http.GET
import retrofit2.Retrofit
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private interface RetrofitWeatherApi {

    @GET("/weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("appid") apiKey: String
    ): CurrentWeatherData
}

class NetworkWeatherApi : WeatherApi {

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(30000, TimeUnit.SECONDS)
            .writeTimeout(30000, TimeUnit.SECONDS)
            .build()
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(OPEN_WEATHER_BASE_URL)
            .client(okHttpClient)
            .build()
    }
    private val weatherApiInterface by lazy { retrofit.create(RetrofitWeatherApi::class.java) }

    override suspend fun getCurrentWeather(latitude: Float, longitude: Float): CurrentWeatherData {
        return weatherApiInterface.getCurrentWeather(latitude, longitude, "")
    }

    companion object {
        private const val OPEN_WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5"
    }
}