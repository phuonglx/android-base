package com.studiounknown.data.remote

import com.studiounknown.model.WeatherResponse
import io.reactivex.Single

interface Api {

    //Function on weather
    fun getWeathers(query: String): Single<WeatherResponse>
}
