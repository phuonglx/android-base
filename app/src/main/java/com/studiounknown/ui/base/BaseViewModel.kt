package com.studiounknown.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studiounknown.common.LoadingState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    val loading = MutableLiveData<LoadingState>()
    val message = MutableLiveData<String>()
    private val disposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        this.disposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
