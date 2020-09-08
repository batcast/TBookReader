package com.example.tbookreader

import androidx.multidex.MultiDexApplication
import com.example.tbookreader.data.AppDatabase

class App : MultiDexApplication() {

    companion object {
        @JvmStatic
        lateinit var INSTANCE: App
            private set

        @JvmStatic
        lateinit var db: AppDatabase
            private set
    }

    var versionCode = 0
    var versionName = ""

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        db = AppDatabase.createDatabase(INSTANCE)
    }
}