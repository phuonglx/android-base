package com.studiounknown.data.local.pref

interface Preference {

    fun clear()

    fun getFirstRun(): Boolean

    fun saveFirstRun()
}
