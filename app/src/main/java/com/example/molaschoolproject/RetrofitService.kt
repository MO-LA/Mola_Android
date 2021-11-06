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

    @GET("school/list")
    fun getSchoolData(
        @Query("size") size: Int = 5000
    ): Call<SchoolData>

    @GET("school/list/name")
    fun getSchoolDataByName(
        @Query("size") size: Int = 5000,
        @Query("q") q: String
    ): Call<SchoolData>

    @GET("school/list/kind")
    fun getSchoolDataByKind(
        @Query("size") size: Int = 5000,
        @Query("schoolKind") schoolKind: String
    ) : Call<SchoolData>

    @GET("school/list/fond")
    fun getSchoolDataByFond(
        @Query("size") size: Int = 5000,
        @Query("fond") fond: String
    ) : Call<SchoolData>

    @GET("school/list/fondType")
    fun getSchoolDataByFondType(
        @Query("size") size: Int = 5000,
        @Query("fondType") fondType: String
    ) : Call<SchoolData>
    
    @POST("review")
    fun postReview(
        @Body review:SendReview
    ): Call<Any?>



    @GET("review/list")
    fun getReviewList(
        @Query("schoolIdx") schoolIdx: Int
    ) : Call<Review?>

    @POST("/post")
    fun postCommunity(
        @Body communityWrite: CommunityWrite
    ) : Call<CommunityWrite>

    @GET("/post/list")
    fun getCommunity(
        @Query("size") size: Int = 10000
    ): Call<CommunityData>

    @POST("/comment")
    fun postComment(
        @Body commentWrite: CommentWrite
    ): Call<CommentWrite>

    @GET("/comment/list")
    fun getComment(
        @Query("postIdx") postIdx: Int
    ): Call<Comment>

    @PATCH("/estimate")
    fun PatchEstimate(
        @Query("estimate") estimate: Int,
        @Query("schoolIdx") schoolIdx: Int
    ) : Call<Any?>

    @GET("/user")
    fun getUser(): Call<User>

}