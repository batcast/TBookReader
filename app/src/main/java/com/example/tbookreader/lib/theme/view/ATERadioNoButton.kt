package com.example.tbookreader.lib.theme.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.example.tbookreader.R
import com.example.tbookreader.lib.theme.Selector
import com.example.tbookreader.lib.theme.ThemeStore


/**
 * @author Aidan Follestad (afollestad)
 */
class ATERadioNoButton(context: Context, attrs: AttributeSet) :
    AppCompatRadioButton(context, attrs) {

    init {
        background = Selector.shapeBuild()
            .setCornerRadius(2.dp)
            .setStrokeWidth(2.dp)
            .setCheckedBgColor(ThemeStore.accentColor(context))
            .setCheckedStrokeColor(ThemeStore.accentColor(context))
            .setDefaultStrokeColor(context.getCompatColor(R.color.tv_text_default))
            .create()
    }
}
