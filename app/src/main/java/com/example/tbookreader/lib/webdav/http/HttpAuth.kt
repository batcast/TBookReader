package com.example.tbookreader.lib.webdav.http

object HttpAuth {

    var auth: Auth? = null

    class Auth internal constructor(val user: String, val pass: String)
}