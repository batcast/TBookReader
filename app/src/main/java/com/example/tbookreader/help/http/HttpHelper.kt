package com.example.tbookreader.help.http

import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import java.util.concurrent.TimeUnit

object HttpHelper {

    val client:OkHttpClient by lazy {

        val specs = arrayListOf(
                ConnectionSpec.MODERN_TLS,
                ConnectionSpec.COMPATIBLE_TLS,
                ConnectionSpec.CLEARTEXT
        )

        val builder = OkHttpClient.Builder()
                .connectTimeout(15,TimeUnit.SECONDS)
                .writeTimeout(15,TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .sslSocketFactory(SSLHelper.unsafeSSLSocketFactory,SSLHelper.unsafeTrustManager)
                .retryOnConnectionFailure(true)
                .hostnameVerifier(SSLHelper.unsafeHostnameVerifier)
                .connectionSpecs(specs)
                .followRedirects(true)
                .followSslRedirects(true)
                .protocols(listOf(Protocol.HTTP_1_1))
                .addInterceptor(getHeaderInterceptor())
        builder.build()
    }



    private fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("Keep-Alive", "300")
                    .addHeader("Connection", "Keep-Alive")
                    .addHeader("Cache-Control", "no-cache")
                    .build()
            chain.proceed(request)
        }
    }

}