package com.example.molaschoolproject

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateRetrofit {

    fun hasTokenRetrofit(): RetrofitService {

        val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://10.80.162.195:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        return service
    }

    fun noHeaderRetrofit(): RetrofitService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.80.162.195:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        return service
    }

}