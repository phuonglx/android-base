package com.studiounknown.ui

import android.app.Application
import com.studiounknown.di.modules
import org.koin.android.ext.android.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, modules)
    }
}
