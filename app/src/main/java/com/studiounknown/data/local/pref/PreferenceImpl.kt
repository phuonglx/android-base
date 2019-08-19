package com.studiounknown.data.local.pref

import android.content.Context
import com.studiounknown.util.FIRST_RUN

class PreferenceImpl(context: Context) : Preference {
    private val sharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    override fun getFirstRun(): Boolean = sharedPreferences.getBoolean(FIRST_RUN, false)

    override fun saveFirstRun() {
        sharedPreferences.edit().putBoolean(FIRST_RUN, true).apply()
    }
}
