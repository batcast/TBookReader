package com.example.tbookreader.utils


import com.example.tbookreader.App
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.*
import java.util.logging.Formatter

object LogUtils {
    const val TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"

    //懒加载fileHandler
    private val fileHandler by lazy {
        val root = App.INSTANCE.externalCacheDir ?: return@lazy null
        val logFolder = FileUtils.createFolderIfNotExist(root, "logs")
        val logPath = FileUtils.getPath(logFolder, "appLog")
        FileHandler(logPath, 10240, 10).apply {
            formatter = object : Formatter() {
                override fun format(record: LogRecord): String {
                    //设置文件输出格式
                    return (getCurrentDateStr(TIME_PATTERN) + ": " + record.message + "\n")
                }
            }
            level = if (App.INSTANCE.getPrefBoolean("recordLog")) {
                Level.INFO
            } else {
                Level.OFF
            }
        }
    }

    fun upLevel() {
        fileHandler?.level = if (App.INSTANCE.getPrefBoolean("recordLog")) {
            Level.INFO
        } else {
            Level.OFF
        }
    }

    //懒加载logger
    private val logger: Logger by lazy {
        Logger.getGlobal().apply {
            fileHandler?.let {
                addHandler(it)
            }
        }
    }

    @JvmStatic
    fun d(tag: String, msg: String) {
        logger.log(Level.INFO,"$tag $msg")
    }

    fun e(tag:String,msg: String){
        logger.log(Level.WARNING,"$tag $msg")
    }

    /*获取当前时间*/
    fun getCurrentDateStr(pattern: String) = SimpleDateFormat(pattern).format(Date())
}