package com.example.tbookreader.help

import android.annotation.SuppressLint
import android.content.Context
import com.example.tbookreader.App
import com.example.tbookreader.R
import com.example.tbookreader.constant.PreferKey
import com.example.tbookreader.utils.*

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
                App.INSTANCE.putPrefString(PreferKey.themeMode, "2")
            } else {
                App.INSTANCE.putPrefString(PreferKey.themeMode, "1")
            }
        }

    val isEInkMode: Boolean
        get() = App.INSTANCE.getPrefString(PreferKey.themeMode) == "3"

    var isTransparentStatusBar: Boolean
        get() = App.INSTANCE.getPrefBoolean(PreferKey.transparentStatusBar)
        set(value) {
            App.INSTANCE.putPrefBoolean(PreferKey.transparentStatusBar, value)
        }

    val requestedDirection: String?
        get() = App.INSTANCE.getPrefString("list_screen_direction")

    var backupPath: String?
        get() = App.INSTANCE.getPrefString(PreferKey.backupPath)
        set(value) {
            if (value.isNullOrEmpty()) {
                App.INSTANCE.removePref(PreferKey.backupPath)
            } else {
                App.INSTANCE.putPrefString(PreferKey.backupPath, value)
            }
        }

    var isShowRSS: Boolean
        get() = App.INSTANCE.getPrefBoolean(PreferKey.showRss, true)
        set(value) {
            App.INSTANCE.putPrefBoolean(PreferKey.showRss, value)
        }

    val autoRefreshBook: Boolean
        get() = App.INSTANCE.getPrefBoolean("auto_refresh")

    var threadCount: Int
        get() = App.INSTANCE.getPrefInt(PreferKey.threadCount, 1)
        set(value) {
            App.INSTANCE.putPrefInt(PreferKey.threadCount, value)
        }

    var importBookPath: String?
        get() = App.INSTANCE.getPrefString("importBookPath")
        set(value) {
            if (value == null) {
                App.INSTANCE.removePref("importBookPath")
            } else {
                App.INSTANCE.putPrefString("importBookPath", value)
            }
        }

    var ttsSpeechRate: Int
        get() = App.INSTANCE.getPrefInt(PreferKey.ttsSpeechRate, 5)
        set(value) = App.INSTANCE.putPrefInt(PreferKey.ttsSpeechRate, value)

    val ttsSpeechPer: String
        get() = App.INSTANCE.getPrefString(PreferKey.ttsSpeechPer) ?: "0"

    val clickAllNext:Boolean get() = App.INSTANCE.getPrefBoolean(PreferKey.clickAllNext,false)

    var chineseConverterType: Int
        get() = App.INSTANCE.getPrefInt(PreferKey.chineseConverterType)
        set(value) {
            App.INSTANCE.putPrefInt(PreferKey.chineseConverterType, value)
        }

    var systemTypefaces: Int
        get() = App.INSTANCE.getPrefInt(PreferKey.systemTypefaces)
        set(value) {
            App.INSTANCE.putPrefInt(PreferKey.systemTypefaces, value)
        }

    var bookGroupAllShow: Boolean
        get() = App.INSTANCE.getPrefBoolean("bookGroupAll", true)
        set(value) {
            App.INSTANCE.putPrefBoolean("bookGroupAll", value)
        }

    var bookGroupLocalShow: Boolean
        get() = App.INSTANCE.getPrefBoolean("bookGroupLocal", false)
        set(value) {
            App.INSTANCE.putPrefBoolean("bookGroupLocal", value)
        }

    var bookGroupAudioShow: Boolean
        get() = App.INSTANCE.getPrefBoolean("bookGroupAudio", false)
        set(value) {
            App.INSTANCE.putPrefBoolean("bookGroupAudio", value)
        }

    var bookGroupNoneShow: Boolean
        get() = App.INSTANCE.getPrefBoolean("bookGroupNone", false)
        set(value) {
            App.INSTANCE.putPrefBoolean("bookGroupNone", value)
        }

    var elevation: Int
        @SuppressLint("PrivateResource")
        get() = App.INSTANCE.getPrefInt(
                PreferKey.barElevation,
                App.INSTANCE.resources.getDimension(R.dimen.design_appbar_elevation).toInt()
        )
        set(value) {
            App.INSTANCE.putPrefInt(PreferKey.barElevation, value)
        }


    val autoChangeSource: Boolean get() = App.INSTANCE.getPrefBoolean("autoChangeSource", true)

    val readBodyToLh: Boolean get() = App.INSTANCE.getPrefBoolean(PreferKey.readBodyToLh, true)

    val isGooglePlay: Boolean get() = App.INSTANCE.channel == "google"

    val isCoolApk: Boolean get() = App.INSTANCE.channel == "coolApk"
}