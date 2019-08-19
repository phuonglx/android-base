package com.studiounknown.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.studiounknown.model.WeatherModel
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface WeatherDao {

    @Query("SELECT * from weather")
    fun queryWeathers(): Flowable<List<WeatherModel>>

    @Query("SELECT * FROM weather WHERE id = :id")
    fun queryWeather(id: Int): Single<WeatherModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weatherModel: WeatherModel): Long
}
