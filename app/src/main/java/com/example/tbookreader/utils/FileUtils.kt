package com.example.tbookreader.utils

import android.os.Environment
import com.example.tbookreader.App
import java.io.File
import java.io.IOException

object FileUtils {

    //获取文件路径
    fun getPath(root: File, fileName: String? = null, vararg subDirs: String): String {
        val path = StringBuilder(root.absolutePath)
        subDirs.forEach {
            path.append(File.separator).append(it)
        }
        if (!fileName.isNullOrEmpty()) {
            path.append(File.separator).append(fileName)
        }
        return path.toString()
    }

    //获取文件对象
    fun getFile(root: File, fileName: String, vararg subDirs: String) = File(getPath(root, fileName, *subDirs))

    //判断文件是否存在
    fun exists(root: File, fileName: String, vararg subDirs: String) = getFile(root, fileName, subDirs = *subDirs).exists()

    fun createFileIfNotExist(root: File, fileName: String, vararg subDirs: String) = createFileIfNotExist(getPath(root, fileName, *subDirs))

    fun createFileIfNotExist(root: File, vararg subDirs: String) = createFolderIfNotExist(getPath(root, subDirs = *subDirs))

    fun createFolderIfNotExist(filePath: String): File {
        val file = File(filePath)
        //如果文件夹不存在，就创建它
        if (!file.exists()) {
            file.mkdirs()
        }
        return file
    }

    fun createFolderIfNotExist(root: File, vararg subDirs: String) = createFolderIfNotExist(getPath(root, subDirs = *subDirs))

    @Synchronized
    fun createFileIfNotExist(filePath: String): File {
        val file = File(filePath)
        try {
            //如果文件不存在
            if (!file.exists()) {
                //创建父类文件夹
                file.parent?.let {
                    createFolderIfNotExist(it)
                }
                //创建文件
                file.createNewFile()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }

    fun getDirFile(root: File, vararg subDirs: String) = File(getPath(root, subDirs = *subDirs))

    //递归删除文件夹下的数据
    @Synchronized
    fun deleteFile(filePath: String) {
        val file = File(filePath)
        if (!file.exists()) return

        if (file.isDirectory) {
            val files = file.listFiles()
            files?.forEach {
                val path = it.path
                deleteFile(path)
            }
        }
        //删除文件
        file.delete()
    }

    fun getCachePath() = App.INSTANCE.externalCacheDir?.absolutePath
            ?: App.INSTANCE.cacheDir.absolutePath

    fun getSdCardPath(): String {
        @Suppress("DEPRECATION")
        var sdCardDirectory = Environment.getExternalStorageDirectory().absolutePath
        try {
            sdCardDirectory = File(sdCardDirectory).canonicalPath
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
        return sdCardDirectory
    }

}