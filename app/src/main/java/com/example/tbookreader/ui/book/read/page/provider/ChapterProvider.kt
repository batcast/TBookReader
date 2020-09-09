package com.example.tbookreader.ui.book.read.page.provider

import android.graphics.Typeface
import android.text.TextPaint
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



    val TextPaint.textHeight: Float
        get() = fontMetrics.descent - fontMetrics.ascent + fontMetrics.leading
}