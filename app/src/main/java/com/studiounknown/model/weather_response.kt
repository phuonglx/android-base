package com.studiounknown.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("weather")
    val weather: List<Weather>?,
    @Expose
    @SerializedName("main")
    val main: Main?,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("visibility")
    val visibility: Int?
)

data class Weather(
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("main")
    val main: String?,
    @Expose
    @SerializedName("description")
    val description: String?,
    @Expose
    @SerializedName("icon")
    val icon: String?
)

data class Main(
    @Expose
    @SerializedName("temp")
    val temp: Float?,
    @Expose
    @SerializedName("pressure")
    val pressure: Float?,
    @Expose
    @SerializedName("humidity")
    val humidity: Float?,
    @Expose
    @SerializedName("temp_min")
    val tempMax: Float?,
    @Expose
    @SerializedName("temp_max")
    val tempMin: Float?
)
