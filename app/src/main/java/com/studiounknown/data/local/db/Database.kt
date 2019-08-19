package com.studiounknown.data.local.db

import com.studiounknown.model.WeatherModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface Database {

    //Function for WeatherModel
    fun insertWeather(weatherModel: WeatherModel): Completable

    fun queryWeather(name: String): Single<WeatherModel>

    fun queryWeathers(): Flowable<List<WeatherModel>>
}
