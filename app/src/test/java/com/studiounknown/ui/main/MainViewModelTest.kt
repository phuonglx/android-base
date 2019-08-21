package com.studiounknown.ui.main

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.studiounknown.base.BaseViewModelTest
import com.studiounknown.data.Repository
import com.studiounknown.model.WeatherModel
import com.studiounknown.ui.main.MainViewModel
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MainViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var repository: Repository

    private lateinit var viewModel: MainViewModel

    @Before
    override fun setUp() {
        super.setUp()
        whenever(repository.getWeathers()).thenReturn(Flowable.just(listOf()))
        viewModel = MainViewModel(repository)
    }

    @Test
    fun `When search string bellow min length, repo should not query data`() {
        viewModel.search(null)
        viewModel.search("")
        viewModel.search("a")
        viewModel.search("ab")

        verify(repository, never()).getWeather(any(), any())
    }

    @Test
    fun `When search api return result, weather must be updated`() {
        val searchText = "text"
        val weatherModel = WeatherModel(0, "", 0, "", "", "", 0F, 0F, 0F, 0F, 0F)
        given(repository.getWeather(searchText, true)).willReturn(Single.just(weatherModel))

        viewModel.search(searchText)

        val weather = viewModel.weather.value
        verify(repository).getWeather(searchText, true)
        assertEquals(weatherModel, weather)
        verify(repository).saveWeather(weatherModel)
    }

    @Test
    fun `When search api error, local query must be invoke`() {
        val searchText = "text"
        val throwable = Throwable("error")
        val weatherModel = WeatherModel(0, "", 0, "", "", "", 0F, 0F, 0F, 0F, 0F)
        given(repository.getWeather(searchText, true)).willReturn(Single.error(throwable))
        given(repository.getWeather(searchText, false)).willReturn(Single.just(weatherModel))

        viewModel.search(searchText)

        val weather = viewModel.weather.value
        verify(repository).getWeather(searchText, true)
        verify(repository).getWeather(searchText, false)
        assertEquals(weatherModel, weather)
    }

    @Test
    fun `When search api and local error, weather must be null`() {
        val searchText = "text"
        val error = "error"
        val throwable = Throwable(error)
        given(repository.getWeather(searchText, true)).willReturn(Single.error(throwable))
        given(repository.getWeather(searchText, false)).willReturn(Single.error(throwable))

        viewModel.search(searchText)

        val weather = viewModel.weather.value
        verify(repository).getWeather(searchText, true)
        verify(repository).getWeather(searchText, false)
        assertEquals(null, weather)
        assertEquals(error, viewModel.message.value)
    }
}
