package com.studiounknown.data.remote.service

import com.studiounknown.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun queryWeather(
        @Query("appid") appId: String,
        @Query("q") query: String
    ): Single<WeatherResponse>
}
