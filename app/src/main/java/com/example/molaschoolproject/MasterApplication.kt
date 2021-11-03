package com.example.molaschoolproject

import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MasterApplication : Application() {

    lateinit var service: RetrofitService

    override fun onCreate() {
        super.onCreate()

        createRetrofit()
    }

//    class Interceptor : okhttp3.Interceptor {
//        override fun intercept(chain: okhttp3.Interceptor.Chain): Response {
//            var req = chain
//                .request()
//                .newBuilder()
//                .addHeader("Authorization", App.prefs.token ?: "")
//                .build()
//            return chain.proceed(req)
//        }
//    }

    fun createRetrofit() {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(AuthInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.80.162.195:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        service = retrofit.create(RetrofitService::class.java)
    }
}

