package com.studiounknown.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "visibility")
    val visibility: Int?,
    @ColumnInfo(name = "main")
    val main: String?,
    @ColumnInfo(name = "des")
    val description: String?,
    @ColumnInfo(name = "icon")
    val icon: String?,
    @ColumnInfo(name = "temp")
    val temp: Float?,
    @ColumnInfo(name = "pres")
    val pressure: Float?,
    @ColumnInfo(name = "humidity")
    val humidity: Float?,
    @ColumnInfo(name = "temp_min")
    val tempMin: Float?,
    @ColumnInfo(name = "temp_max")
    val tempMax: Float?
)
