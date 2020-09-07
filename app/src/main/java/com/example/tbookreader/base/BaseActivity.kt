package com.example.tbookreader.base

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.tbookreader.R
import com.example.tbookreader.constant.AppConst
import com.example.tbookreader.constant.Theme
import com.example.tbookreader.lib.theme.ATH
import com.example.tbookreader.lib.theme.ColorUtils
import com.example.tbookreader.lib.theme.backgroundColor
import com.example.tbookreader.lib.theme.primaryColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

abstract class BaseActivity(
        private val layoutID: Int,
        private val fullScreen: Boolean = true,
        private val theme: Theme = Theme.Auto
) : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreateView(
            parent: View?, name: String, context: Context, attrs: AttributeSet
    ): View? {
        if (AppConst.menuViewNames.contains(name) && parent?.parent is FrameLayout) {
            (parent.parent as View).setBackgroundColor(backgroundColor)
        }
        return super.onCreateView(parent, name, context, attrs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.disableAutoFill()
        initTheme()
        setupSystemBar()
        super.onCreate(savedInstanceState)
        setContentView(layoutID)
        onActivityCreated(savedInstanceState)
        observeLiveBus()
    }

    /**
     * 初始化主题
     * @author 汪凯
     * @since 2020年8月28日 11:44:55
     */
    private fun initTheme() {
        ATH.applyBackgroundTint(window.decorView)
        when (theme) {
            Theme.Dark -> setTheme(R.style.AppTheme_Dark)
            Theme.Light -> setTheme(R.style.AppTheme_Light)
            else -> if (ColorUtils.isColorLight(primaryColor)) {
                setTheme(R.style.AppTheme_Light)
            } else {
                setTheme(R.style.AppTheme_Dark))
            }
        }
    }

    private fun setupSystemBar() {
        if (fullScreen) {
            window.clearFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                            or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            )
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }

    }

    open fun upNavigationBarColor() {
        ATH.setNavigationBarColorAuto(this)
    }

    abstract fun onActivityCreated(savedInstanceState: Bundle?)

    open fun observeLiveBus() {}

    override fun finish() {
        currentFocus?.hideSoftInput()
        super.finish()
    }
}