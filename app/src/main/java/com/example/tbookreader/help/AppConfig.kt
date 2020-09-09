package com.example.tbookreader.help

import android.content.Context
import com.example.tbookreader.App
import com.example.tbookreader.constant.PreferKey
import com.example.tbookreader.utils.getPrefString
import com.example.tbookreader.utils.sysIsDarkMode

object AppConfig {
    fun isNightTheme(context: Context) = when (context.getPrefString(PreferKey.themeMode, "0")) {
        "1" -> false
        "2" -> true
        "3" -> false
        else -> context.sysIsDarkMode()
    }

    var isNightTheme: Boolean
        get() = isNightTheme(App.INSTANCE)
        set(value) {
            if (value) {
            App.INSTANCE.putP
            }
        }
}