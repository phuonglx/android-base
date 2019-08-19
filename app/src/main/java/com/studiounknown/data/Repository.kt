package com.studiounknown.data

import com.studiounknown.model.WeatherModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface Repository {
    //Function on preference
    fun isFirstRun(): Boolean

    fun saveFirstRun()

    //Function on weather
    fun getWeather(name: String, fromRemote: Boolean): Single<WeatherModel>

    fun saveWeather(weatherModel: WeatherModel): Completable

    fun getWeathers(): Flowable<List<WeatherModel>>
}
