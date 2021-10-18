package com.example.molaschoolproject

import com.example.molaschoolproject.data_type.Login
import com.example.molaschoolproject.data_type.SignUp
import com.example.molaschoolproject.data_type.User
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @POST("auth/")
    fun signup(
        @Body signUp: SignUp
    ): Call<Any?>

    @POST("auth/login/")
    fun login(
        @Body login: Login
    ): Call<User>

    @POST("auth/DV/")
    fun overlapID(
        @Query("id") id: String
    ): Call<Any?>

}