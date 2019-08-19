package com.studiounknown.data.local.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.studiounknown.model.WeatherModel
import com.studiounknown.util.DB_VERSION

@Database(entities = [WeatherModel::class], version = DB_VERSION, exportSchema = false)
abstract class DAO : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}
