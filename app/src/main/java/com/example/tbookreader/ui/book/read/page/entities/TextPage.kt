package com.example.tbookreader.ui.book.read.page.entities

import com.example.tbookreader.ui.book.read.page.provider.ChapterProvider

data class TextPage(
    var index: Int = 0,
    var text: String = "Loading...",
    var title: String = "",
    val textLines: ArrayList<TextLine> = arrayListOf(),
    var pageSize: Int = 0,
    var chapterSize: Int = 0,
    var chapterIndex: Int = 0,
    var height: Float = 0f
) {
    fun upLinesPosition() = ChapterProvider.apply {

    }
}