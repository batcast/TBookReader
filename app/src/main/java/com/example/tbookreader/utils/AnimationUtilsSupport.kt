package com.example.tbookreader.utils

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import com.example.tbookreader.help.AppConfig

object AnimationUtilsSupport {

    /**
     * 加载anim目录下的动画，如果是墨水模式，动画时间设置为0
     *
     * @param context
     * @param id
     * @return
     */
    fun loadAnimation(context: Context, @AnimRes id: Int): Animation {
        val animation = AnimationUtils.loadAnimation(context, id)
        if (AppConfig.isEInkMode) {
            animation.duration = 0
        }
        return animation
    }
}