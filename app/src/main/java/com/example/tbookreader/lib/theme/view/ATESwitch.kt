package com.example.tbookreader.lib.theme.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SwitchCompat
import com.example.tbookreader.lib.theme.ATH
import com.example.tbookreader.lib.theme.accentColor

/**
 * @author Aidan Follestad (afollestad)
 */
class ATESwitch(context: Context, attrs: AttributeSet) : SwitchCompat(context, attrs) {

    init {
        ATH.setTint(this, context.accentColor)
    }

}
