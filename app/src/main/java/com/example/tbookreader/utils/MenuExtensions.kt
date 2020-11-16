package com.example.tbookreader.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuItemImpl
import androidx.core.view.forEach
import com.example.tbookreader.R
import com.example.tbookreader.constant.Theme
import com.example.tbookreader.lib.theme.DrawableUtils
import java.lang.Exception
import java.lang.reflect.Method

@SuppressLint("RestrictedApi")
fun Menu.applyTint(context: Context, theme: Theme = Theme.Auto): Menu = this.let {
    if (it is MenuBuilder) {
        it.setOptionalIconsVisible(true)
    }
    val defaultTextColor = context.getCompatColor(R.color.tv_text_default)
    val tintColor = UIUtils.getMenuColor(context, theme)
    it.forEach { item ->
        (item as MenuItemImpl).let { impl ->
            DrawableUtils.setTint(
                    impl.icon,
                    if (impl.requiresOverflow()) defaultTextColor
                    else tintColor
            )
        }
    }
    return it
}

fun Menu.applyOpenTint(context: Context) {
    //展开菜单显示图标
    if (this.javaClass.simpleName.equals("MenuBuilder", ignoreCase = true)) {
        val defaultTextColor = context.getCompatColor(R.color.tv_text_default)
        try {
            var method: Method =
                    this.javaClass.getDeclaredMethod("setOptionalIconVisible", java.lang.Boolean.TYPE)
            method.isAccessible = true
            method.invoke(this, true)
            method = this.javaClass.getDeclaredMethod("getNonActionItems")
            val menuItems = method.invoke(this)
            if (menuItems is ArrayList<*>) {
                for (menuItem in menuItems) {
                    if (menuItem is MenuItem) {
                        DrawableUtils.setTint(
                                menuItem.icon,
                                defaultTextColor
                        )
                    }
                }
            }
        } catch (ignored: Exception) {
        }
    }
}