package com.studiounknown.data.remote

import com.studiounknown.BuildConfig
import com.studiounknown.data.remote.service.WeatherService
import com.studiounknown.model.WeatherResponse
import io.reactivex.Single

class ApiImpl(
    private val weatherService: WeatherService
) : Api {
    override fun getWeathers(query: String): Single<WeatherResponse> =
        weatherService.queryWeather(BuildConfig.APP_ID, query)
}
