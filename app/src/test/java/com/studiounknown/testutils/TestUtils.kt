package com.studiounknown.testutils

import android.annotation.TargetApi
import android.os.Build
import com.google.gson.Gson
import java.nio.file.Paths

open class TestUtils {
    companion object {

        // get string json from `resources` folder
        @TargetApi(Build.VERSION_CODES.O)
        fun getStringJson(fileName: String): String {
            val res = this::class.java.classLoader?.getResource(fileName)
            val file = Paths.get(res?.toURI()).toFile()
            return String(file.readBytes())
        }

        // get json objects from resource file
        @TargetApi(Build.VERSION_CODES.O)
        fun <T> getObjectFromFile(fileName: String, clazz: Class<T>): T? {
            val uri = this::class.java.classLoader?.getResource(fileName)
            uri?.let {
                val file = Paths.get(uri.toURI()).toFile()
                return Gson().fromJson<T>(String(file.readBytes()), clazz)
            }
            return null
        }
    }
}
