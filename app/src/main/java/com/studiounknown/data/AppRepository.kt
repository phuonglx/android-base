package com.studiounknown.data

import com.studiounknown.data.local.db.Database
import com.studiounknown.data.local.pref.Preference
import com.studiounknown.data.remote.Api
import com.studiounknown.model.WeatherModel
import com.studiounknown.model.mapper.toModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class AppRepository(
    private val database: Database,
    private val preference: Preference,
    private val api: Api
) : Repository {

    override fun isFirstRun(): Boolean = preference.getFirstRun()

    override fun saveFirstRun() = preference.saveFirstRun()

    override fun saveWeather(weatherModel: WeatherModel): Completable =
        database.insertWeather(weatherModel)

    override fun getWeather(name: String, fromRemote: Boolean): Single<WeatherModel> =
        if (fromRemote) {
            api.getWeathers(query = name).map {
                it.toModel()
            }
        } else {
            database.queryWeather(1)
        }

    override fun getWeathers(): Flowable<List<WeatherModel>> = database.queryWeathers()
}
