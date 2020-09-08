package com.example.tbookreader.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * 将字符串转化为MD5
 */

object MD5Utils {

    fun md5Encode(str: String?): String {
        str ?: return ""
        val sb = StringBuilder()
        try {
            val md5 = MessageDigest.getInstance("MD5")
            val bytes = md5.digest(str.toByteArray())
            for (b in bytes) {
                val bt = b.toInt() and 0xff
                if (bt < 16) {
                    sb.append(0)
                }
                sb.append(Integer.toHexString(bt))
            }
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return sb.toString()
    }

    fun md5Encode16(str: String) = md5Encode(str).substring(8, 24)

}