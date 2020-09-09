package com.example.tbookreader.ui.book.read.page.entities

import android.text.TextPaint
import com.example.tbookreader.ui.book.read.page.provider.ChapterProvider
import com.example.tbookreader.ui.book.read.page.provider.ChapterProvider.textHeight

data class TextLine(
    var text: String = "",
    val textChars: ArrayList<TextChar> = arrayListOf(),
    var lineTop: Float = 0f,
    var lineBase: Float = 0f,
    var lineBottom: Float = 0f,
    val isTitle: Boolean = false,
    val isImage: Boolean = false,
    var isReadAloud: Boolean = false
) {
    fun upTopBottom(durY: Float, textPaint: TextPaint) {
        lineTop = ChapterProvider.paddingTop + durY
        lineBottom = lineTop + textPaint.textHeight
        lineBase = lineBottom - textPaint.fontMetrics.descent
    }

    fun addTextChar(charData: String, start: Float, end: Float) {
        textChars.add(TextChar(charData, start, end))
    }

    fun getTextCharAt(index: Int) = textChars[index]

    fun getTextCharReverseAt(index: Int) = textChars[textChars.lastIndex - index]

    fun getTextCharsCount() = textChars.size
}