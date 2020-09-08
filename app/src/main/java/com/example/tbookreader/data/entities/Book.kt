package com.example.tbookreader.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.tbookreader.constant.BookType
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
        tableName = "books",
        indices = [Index(value = ["name", "author"], unique = true)]
)
data class Book(
        @PrimaryKey
        override var bookUrl: String = "",//详情页URL(本地书籍存储完整文件路径)
        var tocUrl: String = "",
        var origin: String = BookType.local,
        var originName: String = "",
        var name: String = "",
        var author: String = "",
        override var kind: String? = null,
        var customTag: String? = null,
        var coverUrl: String? = null,



        override var wordCount: String?,



        ):Parcelable,BaseBook {

        override var infoHtml: String? = null

        override var tocHtml: String? = null

        override fun putVariable(key: String, value: String) {
                TODO("Not yet implemented")
        }

        override val variableMap: HashMap<String, String> = TODO()


}