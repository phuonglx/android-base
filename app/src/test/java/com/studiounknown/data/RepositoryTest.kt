package com.studiounknown.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.studiounknown.base.BaseRepositoryTest
import com.studiounknown.data.local.db.Database
import com.studiounknown.data.local.pref.Preference
import com.studiounknown.data.remote.ApiImpl
import com.studiounknown.data.remote.service.WeatherService
import com.studiounknown.model.WeatherModel
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import java.net.HttpURLConnection.HTTP_OK

class RepositoryTest : BaseRepositoryTest() {

    companion object {
        private const val WEATHER_SUCCESS = "api-response/weather_response.json"
    }

    @Mock
    private lateinit var database: Database
    @Mock
    private lateinit var preference: Preference

    private lateinit var repository: Repository

    override fun setUp() {
        super.setUp()
        repository = AppRepository(
            database,
            preference,
            ApiImpl(retrofit.create(WeatherService::class.java))
        )
    }

    @Test
    fun `When get weather from api return weather, weather must be return`() {
        fakeResponseBodyFile(WEATHER_SUCCESS, HTTP_OK)
        val name = "name"

        val testObserver = TestObserver<WeatherModel>()
        repository.getWeather(name, true).subscribe(testObserver)

        testObserver.assertComplete()
        assertEquals("Hanoi", testObserver.values().firstOrNull()?.name)
        verify(database, never()).queryWeather(name)
    }

    @Test
    fun `When get weather from local, local must invoked`() {
        fakeResponseBodyFile(WEATHER_SUCCESS, HTTP_OK)
        val name = "name"
        val weatherModel = WeatherModel(0, "", 0, "", "", "", 0F, 0F, 0F, 0F, 0F)
        given(database.queryWeather(name)).willReturn(Single.just(weatherModel))

        val testObserver = TestObserver<WeatherModel>()
        repository.getWeather(name, false).subscribe(testObserver)

        testObserver.assertComplete()
        verify(database).queryWeather(name)
    }
}
