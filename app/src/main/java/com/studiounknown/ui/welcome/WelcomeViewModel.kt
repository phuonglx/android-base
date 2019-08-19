package com.studiounknown.ui.welcome

import com.studiounknown.common.SingleLiveData
import com.studiounknown.data.Repository
import com.studiounknown.ui.base.BaseViewModel

class WelcomeViewModel(
    private val repository: Repository
) : BaseViewModel() {

    val isFirstRun = SingleLiveData<Boolean>()

    init {
        isFirstRun.value = repository.isFirstRun()
    }

    fun saveFirstRun() {
        repository.saveFirstRun()
    }
}
