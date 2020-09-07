package com.example.tbookreader.lib.theme.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.example.tbookreader.lib.theme.ATH
import com.example.tbookreader.lib.theme.accentColor

/**
 * @author Aidan Follestad (afollestad)
 */
class ATECheckBox(context: Context, attrs: AttributeSet) : AppCompatCheckBox(context, attrs) {

    init {
        ATH.setTint(this, context.accentColor)
    }
}
