package com.studiounknown.ui.main

import androidx.lifecycle.MutableLiveData
import com.studiounknown.data.Repository
import com.studiounknown.model.WeatherModel
import com.studiounknown.ui.base.BaseViewModel
import com.studiounknown.util.add
import com.studiounknown.util.applyApiSchedulers
import com.studiounknown.util.applyApiSchedulersAsFlowable
import com.studiounknown.util.applyApiSchedulersCompletable
import com.studiounknown.util.applySchedulersSingle
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val repository: Repository
) : BaseViewModel() {

    companion object {
        const val DEBOUNCE_TIME = 300L //ms
        const val MIN_LENGTH_SEARCH = 3
    }

    val weather = MutableLiveData<WeatherModel?>()
    val recentWeathers = MutableLiveData<List<WeatherModel>>()
    private var publishSearchText: PublishSubject<String>? = null

    init {
        repository.getWeathers()
            .compose(applyApiSchedulersAsFlowable())
            .subscribe({
                recentWeathers.value = it
            }, {
                recentWeathers.value = listOf()
            })
            .add(this)
    }

    fun search(name: String?) {
        if (name.isNullOrEmpty()) {
            return
        }
        if (publishSearchText == null) {
            publishSearchText = PublishSubject.create()
            publishSearchText!!.debounce(DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
                .filter {
                    !it.isBlank() && it.length >= MIN_LENGTH_SEARCH
                }
                .distinctUntilChanged()
                .switchMap {
                    repository.getWeather(it, true).toObservable()
                        .compose(applyApiSchedulers(loading))
                        .doOnError {
                            getWeatherLocal(name)
                        }
                        .onErrorResumeNext(Observable.empty())
                }
                .subscribe({
                    this.weather.value = it
                    saveSearchResult(it)
                }, {})
                .add(this)
        }
        publishSearchText!!.onNext(name)
    }

    private fun getWeatherLocal(name: String) {
        repository.getWeather(name, false)
            .compose(applySchedulersSingle())
            .subscribe({
                weather.value = it
            }, {
                message.value = it.message
                weather.value = null
            })
            .add(this)
    }

    private fun saveSearchResult(weatherModel: WeatherModel) {
        repository.saveWeather(weatherModel)
            .compose(applyApiSchedulersCompletable())
            .subscribe()
            .add(this)
    }
}
