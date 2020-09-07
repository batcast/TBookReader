package com.example.tbookreader.lib.theme.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.example.tbookreader.lib.theme.ATH
import com.example.tbookreader.lib.theme.accentColor

/**
 * @author Aidan Follestad (afollestad)
 */
class ATERadioButton(context: Context, attrs: AttributeSet) : AppCompatRadioButton(context, attrs) {

    init {
        ATH.setTint(this@ATERadioButton, context.accentColor)
    }
}
