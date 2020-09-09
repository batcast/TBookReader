package com.example.tbookreader.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.tbookreader.constant.AppPattern
import com.example.tbookreader.constant.BookType
import com.example.tbookreader.utils.GSON
import com.example.tbookreader.utils.fromJsonObject
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import kotlin.math.max

@Parcelize
@Entity(
    tableName = "books",
    indices = [Index(value = ["name", "author"], unique = true)]
)
data class Book(
    @PrimaryKey
    override var bookUrl: String = "",     //详情页URL(本地书籍存储完整文件路径)
    var tocUrl: String = "",               //目录页URL(toc = table of Contents)
    var origin: String = BookType.local,   //书源URL（默认BookType.local）
    var originName: String = "",           //书源名称 或 本地书籍文件名
    var name: String = "",                 //书籍名称（书源获取）
    var author: String = "",               //作者名称（书源获取）
    override var kind: String? = null,     //分类信息（书源获取）
    var customTag: String? = null,         //分类信息（用户修改）
    var coverUrl: String? = null,          //封面URL（书源获取）
    var customCoverUrl: String? = null,       //封面URL（用户修改）
    var intro: String? = null,                //简介内容（书源获取）
    var customIntro: String? = null,          //简介内容（用户修改）
    var charset: String? = null,             //自定义字符集名称（仅适用于本地书籍）
    var type: Int = 0,                       //0:text   1:audio
    var group: Int = 0,                      //自定义分组索引号
    var latestChapterTitle: String? = null,       //最新章节标题
    var latestChapterTime: Long = System.currentTimeMillis(),          //最新章节标题更新时间
    var lastCheckTime: Long = System.currentTimeMillis(),              //最近一次更新书籍信息的时间
    var lastCheckCount: Int = 0,                       //最近一次更新书籍信息的时间
    var totalChapterNum: Int = 0,                      //书籍目录总数
    var durChapterTitle: String? = null,               //当前章节名称
    var durChapterIndex: Int = 0,                      //当前章节索引
    var durChapterPos: Int = 0,                        //当前阅读的进度（首行字符的索引位置）
    var durChapterTime: Long = System.currentTimeMillis(),        //最近一次阅读书籍的时间（打开正文的时间）
    override var wordCount: String? = null,
    var canUpdate: Boolean = true,                       //刷新书架时是否更新书籍信息
    var order: Int = 0,                               //手动排序
    var originOrder: Int = 0,                        //书源排序
    var useReplaceRule: Boolean = true,                //正文使用净化替换规则
    var variable: String? = null                    //自定义书籍变量信息（用于书源规则检索书籍信息）
) : Parcelable, BaseBook {

    fun isLocalBook() = origin == BookType.local

    fun isLocalTxt() = isLocalBook() && originName.endsWith(".txt", true)

    fun isEpub() = originName.endsWith(".epub", true)

    fun isOnLineTxt() = !isLocalBook() && type == 0

    override fun equals(other: Any?): Boolean {
        if (other is Book) {
            return other.bookUrl == bookUrl
        }
        return false
    }

    override fun hashCode() = bookUrl.hashCode()


    @Ignore
    @IgnoredOnParcel
    override var infoHtml: String? = null

    @Ignore
    @IgnoredOnParcel
    override var tocHtml: String? = null

    override fun putVariable(key: String, value: String) {
        variableMap[key] = value
        variable = GSON.toJson(variableMap)
    }

    @delegate:Transient
    @delegate:Ignore
    @IgnoredOnParcel
    override val variableMap: HashMap<String, String> by lazy {
        GSON.fromJsonObject<HashMap<String, String>>(variable) ?: HashMap()
    }

    fun getRealAuthor() = author.replace(AppPattern.authorRegex, "")

    fun getUnreadChapterNum() = max(totalChapterNum - durChapterIndex - 1, 0)

    fun getDisplayCover() = if (customCoverUrl.isNullOrEmpty()) coverUrl else customCoverUrl

    fun getDisplayIntro() = if (customIntro.isNullOrEmpty()) intro else customIntro

    fun fileCharset() = charset(charset ?: "UTF-8")

    fun toSearchBook() = S

}