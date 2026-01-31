package com.notifier.app

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class WeatherResponse(
    val hourly: HourlyData
)

data class HourlyData(
    val time: List<String>,
    @SerializedName("temperature_2m") val temperature2m: List<Double>
)

interface WeatherApi {
    @GET("v1/forecast")
    suspend fun getForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = "temperature_2m",
        @Query("timezone") timezone: String = "Europe/Helsinki"
    ): WeatherResponse
}

object WeatherService {
    private val api = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    suspend fun getTemperatureAt6pm(latitude: Double, longitude: Double): Double? {
        val response = api.getForecast(latitude, longitude)
        val timeList = response.hourly.time
        val tempList = response.hourly.temperature2m
        val index = timeList.indexOfFirst { it.endsWith("T18:00") }
        return if (index >= 0 && index < tempList.size) tempList[index] else null
    }
}
