package com.example.tbookreader.ui.book.read.page.provider

import android.graphics.Typeface
import android.os.Build
import android.text.TextPaint
import android.text.TextUtils
import com.example.tbookreader.App
import com.example.tbookreader.constant.PreferKey
import com.example.tbookreader.help.AppConfig
import com.example.tbookreader.help.ReadBookConfig
import com.example.tbookreader.utils.dp
import com.example.tbookreader.utils.getPrefString
import com.example.tbookreader.utils.removePref
import com.example.tbookreader.utils.sp
import java.util.regex.Pattern

object ChapterProvider {

    var viewWidth = 0
    var viewHeight = 0
    var paddingLeft = 0
    var paddingTop = 0
    var visibleWidth = 0
    var visibleHeight = 0
    var visibleRight = 0
    var visibleBottom = 0
    private var lineSpacingExtra = 0
    private var paragraphSpacing =0
    private var titleTopSpacing=0
    private var titleBottomSpacing =0
    var typeface: Typeface = Typeface.SANS_SERIF
    lateinit var titlePaint: TextPaint
    lateinit var contentPaint: TextPaint
    private val srcPattern = Pattern.compile("<img .*?src.*?=.*?\"(.*?)\".*?>", Pattern.CASE_INSENSITIVE)

    /**
     * 更新样式
     */
    fun upStyle() {
        typeface = try {
            val fontPath = App.INSTANCE.getPrefString(PreferKey.readBookFont)
            if (!TextUtils.isEmpty(fontPath)) {
                Typeface.createFromFile(fontPath)
            } else {
                when (AppConfig.systemTypefaces) {
                    1 -> Typeface.SERIF
                    2 -> Typeface.MONOSPACE
                    else -> Typeface.SANS_SERIF
                }
            }
        } catch (e: Exception) {
            App.INSTANCE.removePref(PreferKey.readBookFont)
            Typeface.SANS_SERIF
        }
        // 字体统一处理
        val bold = Typeface.create(typeface, Typeface.BOLD)
        val normal = Typeface.create(typeface, Typeface.NORMAL)
        val (titleFont, textFont) = when (ReadBookConfig.textBold) {
            1 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
                    Pair(Typeface.create(typeface, 900, false), bold)
                else
                    Pair(bold, bold)
            }
            2 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
                    Pair(normal, Typeface.create(typeface, 300, false))
                else
                    Pair(normal, normal)
            }
            else -> Pair(bold, normal)
        }

        //标题
        titlePaint = TextPaint()
        titlePaint.color = ReadBookConfig.textColor
        titlePaint.letterSpacing = ReadBookConfig.letterSpacing
        titlePaint.typeface = titleFont
        titlePaint.textSize = with(ReadBookConfig) { textSize + titleSize }.sp.toFloat()
        titlePaint.isAntiAlias = true
        //正文
        contentPaint = TextPaint()
        contentPaint.color = ReadBookConfig.textColor
        contentPaint.letterSpacing = ReadBookConfig.letterSpacing
        contentPaint.typeface = textFont
        contentPaint.textSize = ReadBookConfig.textSize.sp.toFloat()
        contentPaint.isAntiAlias = true
        //间距
        lineSpacingExtra = ReadBookConfig.lineSpacingExtra
        paragraphSpacing = ReadBookConfig.paragraphSpacing
        titleTopSpacing = ReadBookConfig.titleTopSpacing.dp
        titleBottomSpacing = ReadBookConfig.titleBottomSpacing.dp
        upViewSize()
    }

    /**
     * 更新View尺寸
     */
    fun upViewSize() {
        paddingLeft = ReadBookConfig.paddingLeft.dp
        paddingTop = ReadBookConfig.paddingTop.dp
        visibleWidth = viewWidth - paddingLeft - ReadBookConfig.paddingRight.dp
        visibleHeight = viewHeight - paddingTop - ReadBookConfig.paddingBottom.dp
        visibleRight = paddingLeft + visibleWidth
        visibleBottom = paddingTop + visibleHeight
    }

    val TextPaint.textHeight: Float
        get() = fontMetrics.descent - fontMetrics.ascent + fontMetrics.leading
}