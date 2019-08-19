package com.studiounknown.di

import android.content.res.Resources
import com.studiounknown.ui.MainApplication
import com.studiounknown.ui.main.MainViewModel
import com.studiounknown.ui.welcome.WelcomeViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

//Application modules
val appModule = module(override = true) {
    single { createResource(get()) }
}

fun createResource(application: MainApplication): Resources = application.resources

//ViewModel Modules
val viewModelModule = module(override = true) {
    viewModel { WelcomeViewModel(get()) }
    viewModel { MainViewModel(get()) }
}

//modules
val modules = listOf(
    appModule,
    viewModelModule,
    dataModule
)
