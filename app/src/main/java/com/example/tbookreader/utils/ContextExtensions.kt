package com.example.tbookreader.utils

import android.content.Context
import org.jetbrains.anko.defaultSharedPreferences

fun Context.getPrefBoolean(key: String, defValue: Boolean = false) =
        defaultSharedPreferences.getBoolean(key, defValue)