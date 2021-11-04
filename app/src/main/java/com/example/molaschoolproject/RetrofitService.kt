package com.example.molaschoolproject

import com.example.molaschoolproject.data_type.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @POST("/auth")
    fun signup(
        @Body signUp: SignUp
    ): Call<SignUp>

    @POST("/auth/login")
    fun login(
        @Body login: Login
    ): Call<token>

    @POST("/auth/DV")
    fun overlapID(
        @Body overlap: Overlap
    ): Call<OverlapData>

    @POST("review")
    fun postReview(
        @Body review:SendReview
    ): Call<Any?>

    @GET("")
    fun myPage(
        @Query("id") id: String,
        @Query("school") school:String
    ): Call<Any?>

    @GET("school/list")
    fun getSchoolData(
        @Query("size") size: Int = 5000
    ): Call<SchoolData>

    @GET("review/list")
    fun getReviewList(
        @Query("schoolIdx") schoolIdx: Int
    ) : Call<Review?>

    @GET("/post/list")
    fun getCommunity(): Call<ArrayList<community>>

    @PATCH("/estimate")
    fun PatchEstimate(
        @Query("estimate") estimate: Int,
        @Query("schoolIdx") schoolIdx: Int
    )

}