package com.example.tbookreader.lib.webdav

import com.example.tbookreader.lib.webdav.http.Handler
import com.example.tbookreader.lib.webdav.http.HttpAuth
import okhttp3.*
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

class WebDav @Throws(MalformedURLException::class)
constructor(urlStr:String) {
    companion object {
        //指定返回哪些属性
        private const val DIR =
                """<?xml version = "1.0"?>
                        <a:propfind xmlns:a="DAV:">
                            <a:prop>
                                <a:displayname/>
                                <a:resourcetype/>
                                <a:getcontentlength/>
                                <a:creationdate/>
                                <a:getlastmodified/>
                                %s
                            </a:prop>
                        </a:propfind>"""
    }

    private val url: URL = URL(null,urlStr,Handler)

    private val httpUrl:String? by lazy {
        val raw = url.toString().replace("davs://","https://").replace("dav://","http://")
        try {
            return@lazy URLEncoder.encode(raw,"UTF-8")
                    .replace("\\+".toRegex(),"%20")
                    .replace("%3A".toRegex(),":")
                    .replace("%2F".toRegex(),"/")
        }catch (e:UnsupportedEncodingException){
            e.printStackTrace()
            return@lazy null
        }
    }

    var displayName:String? = null
    var size:Long = 0
    var exists = false
    var parent = ""
    var urlName = ""
        get() {
            if (field.isEmpty()){
                this.urlName = (
                        if (parent.isEmpty()) url.file
                        else url.toString().replace(parent,"")
                        ).replace("/","")
            }
            return field
        }

    fun getPath() = url.toString()

    fun getHost() = url.host

    @Throws(IOException::class)
    fun indexFileInfo():Boolean {
        // TODO: 2020/8/3  0003
        return false
    }

    private fun propFindResponse(propsList:ArrayList<String>, depth:Int = 1): Response? {
        val requestProps = StringBuilder()
        for (p in propsList) {
            requestProps.append("<a:").append(p).append("/>\n")
        }
        val requestPropsStr : String = if (requestProps.toString().isEmpty()) {
            DIR.replace("%s","")
        }else {
            String.format(DIR,requestProps.toString() + "\n")
        }

        httpUrl?.let {url->
            val request = Request.Builder()
                    .url(url)
                    // 添加RequestBody对象，可以只返回的属性。如果设为null，则会返回全部属性
                    // 注意：尽量手动指定需要返回的属性。若返回全部属性，可能后由于Prop.java里没有该属性名，而崩溃。
                    .method(
                            "PROPFIND",
                            RequestBody.create(MediaType.parse("text/plain"),requestPropsStr)
                    )

            HttpAuth.auth?.let {
                request.header(
                        "Authorization",
                        Credentials.basic(it.user,it.pass)
                )
            }

            request.header("Depth",if (depth < 0 ) "infinity" else depth.toString())
            return HttpHelper.client.newCall(request.build()).execute()
        }
        return null
    }


}