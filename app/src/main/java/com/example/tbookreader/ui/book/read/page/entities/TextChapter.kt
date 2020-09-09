package com.example.tbookreader.ui.book.read.page.entities

data class TextChapter(
    val position: Int,
    val title: String,
    val url: String,
    val pages: List<TextPage>,
    val pageLines: List<Int>,
    val pageLengths: List<Int>,
    val chaptersSize: Int
) {
    fun page(index: Int): TextPage? = pages.getOrNull(index)

    val lastPage: TextPage? get() = pages.lastOrNull()

    val lastIndex get() = pages.lastIndex

    val pageSize get() = pages.size

}