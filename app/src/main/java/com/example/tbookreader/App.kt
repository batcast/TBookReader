package com.example.tbookreader

import androidx.multidex.MultiDexApplication

class App : MultiDexApplication() {

    companion object {
        @JvmStatic
        lateinit var INSTANCE: App
            private set

    }
}