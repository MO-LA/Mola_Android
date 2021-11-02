package com.example.molaschoolproject

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class Interceptor : okhttp3.Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain
            .request()
            .newBuilder()
            .addHeader("Authorization", App.prefs.token ?: "")
            .build()
        return chain.proceed(req)
    }
}