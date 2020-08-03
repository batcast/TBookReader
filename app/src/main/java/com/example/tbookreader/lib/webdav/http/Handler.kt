package com.example.tbookreader.lib.webdav.http

import java.net.URL
import java.net.URLConnection
import java.net.URLStreamHandler

object Handler : URLStreamHandler() {

    override fun getDefaultPort(): Int {
        return 80
    }
    public override fun openConnection(p0: URL): URLConnection? {
        return null
    }

}