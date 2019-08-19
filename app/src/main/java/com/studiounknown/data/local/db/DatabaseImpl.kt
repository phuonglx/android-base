package com.studiounknown.data.local.db

import com.studiounknown.data.local.db.dao.DAO
import com.studiounknown.model.WeatherModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class DatabaseImpl(private val DAO: DAO) : Database {

    override fun insertWeather(weatherModel: WeatherModel): Completable {
        return Completable.create {
            val result = DAO.weatherDao().insertWeather(weatherModel)
            if (result != -1L) {
                it.onComplete()
            } else {
                it.onError(Throwable("Insert weather fail"))
            }
        }
    }

    override fun queryWeather(id: Int): Single<WeatherModel> = DAO.weatherDao().queryWeather(id)

    override fun queryWeathers(): Flowable<List<WeatherModel>> = DAO.weatherDao().queryWeathers()
}
