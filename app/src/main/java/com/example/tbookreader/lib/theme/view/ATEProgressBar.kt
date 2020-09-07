package com.example.tbookreader.lib.theme.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import com.example.tbookreader.lib.theme.ATH
import com.example.tbookreader.lib.theme.ThemeStore

/**
 * @author Aidan Follestad (afollestad)
 */
class ATEProgressBar(context: Context, attrs: AttributeSet) : ProgressBar(context, attrs) {

    init {
        ATH.setTint(this, ThemeStore.accentColor(context))
    }
}