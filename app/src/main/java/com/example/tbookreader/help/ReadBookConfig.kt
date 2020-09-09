package com.example.tbookreader.help

import androidx.annotation.Keep
import com.example.tbookreader.App
import com.example.tbookreader.utils.FileUtils

/*阅读界面配置*/
@Keep        //不混淆
object ReadBookConfig {
    const val readConfigFileName = "readConfig.json"
    private val configFilePath = FileUtils.getPath(App.INSTANCE.filesDir, readConfigFileName)
    val configList: ArrayList<Config> = arrayListOf()


    @Keep
    class Config(
        private var bgStr: String = "#EEEEEE",  //白天背景
        private var bgStrNight: String = "#000000", //夜间背景
        private var bgType: Int = 0,   //白天背景类型  0：颜色， 1：assets图片， 2其他图片
        private var bgTypeNight: Int = 0,  //夜间背景类型
        private var darkStatusIcon: Boolean = true,  //白天是否暗色状态栏
        private var darkStatusIconNight: Boolean = false,  //夜间是否暗色状态栏
        private var textColor: String = "#3E3D3B",  //白天文字颜色
        private var textColorNight: String = "#ADADAD",   //夜间文字颜色

        var textBold: Int = 0,  //是否粗细体，0：正常，1：粗体，2：细体
        var textSize: Int = 20,  //文字大小
        var letterSpacing: Float = 0.1f,   //字间距
        var lineSpacingExtra: Int = 12,   //行间距
        var paragraphSpacing: Int = 4,    //段间距
        var titleMode: Int = 0,   //标题模式  0：居中
        var titleSize: Int = 0,
        var titleTopSpacing: Int = 0,
        var titleBottomSpacing: Int = 0,
        var paddingBottom: Int = 6,
        var paddingLeft: Int = 16,
        var paddingRight: Int = 16,
        var paddingTop: Int = 6,
        var headerPaddingBottom: Int = 0,
        var headerPaddingLeft: Int = 16,
        var headerPaddingRight: Int = 16,
        var headerPaddingTop: Int = 0,
        var footerPaddingTop: Int = 6,
        var footerPaddingBottom: Int = 6,
        var footerPaddingLeft: Int = 16,
        var footerPaddingRight: Int = 16,
        var showHeaderLine: Boolean = false,
        var showFooterLine: Boolean = true
    ) {
        fun setBg(bgType: Int, bg: String) {
    if(AppConfig.isNightTheme())
        }
    }
}