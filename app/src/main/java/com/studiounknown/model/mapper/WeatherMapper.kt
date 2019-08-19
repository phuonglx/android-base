package com.studiounknown.model.mapper

import com.studiounknown.model.WeatherModel
import com.studiounknown.model.WeatherResponse

fun WeatherResponse.toModel(): WeatherModel =
    WeatherModel(
        id ?: -1,
        name,
        visibility,
        weather?.firstOrNull()?.main,
        weather?.firstOrNull()?.description,
        weather?.firstOrNull()?.icon,
        main?.temp,
        main?.pressure,
        main?.humidity,
        main?.tempMin,
        main?.tempMax
    )
