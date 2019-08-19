package com.studiounknown.di

import android.content.Context
import androidx.room.Room
import com.studiounknown.BuildConfig
import com.studiounknown.data.AppRepository
import com.studiounknown.data.Repository
import com.studiounknown.data.local.db.dao.DAO
import com.studiounknown.data.local.db.Database
import com.studiounknown.data.local.db.DatabaseImpl
import com.studiounknown.data.local.pref.Preference
import com.studiounknown.data.local.pref.PreferenceImpl
import com.studiounknown.data.remote.Api
import com.studiounknown.data.remote.ApiImpl
import com.studiounknown.data.remote.service.WeatherService
import com.studiounknown.util.DB_NAME
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module(override = true) {
    single<Preference> { PreferenceImpl(get()) }

    single { createDao(DB_NAME, get()) }
    single<Database> { DatabaseImpl(get()) }

    single { createWeatherService() }
    single<Api> { ApiImpl(get()) }

    single<Repository> { AppRepository(get(), get(), get()) }
}

fun createDao(name: String, context: Context) =
    Room.databaseBuilder(context, DAO::class.java, name).build()

fun createRetrofit(): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                ).build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

fun createWeatherService(): WeatherService = createRetrofit().create(WeatherService::class.java)
