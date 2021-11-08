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
        @Query("size") size: Int = 100
    ): Call<SchoolData>

    @GET("school/list/name")
    fun getSchoolDataByName(
        @Query("size") size: Int = 5000,
        @Query("q") q: String
    ): Call<SchoolData>

    @GET("school/middle")
    fun getMiddleSchoolDataByName(
        @Query("size") size: Int = 5000,
        @Query("q") q: String
    ): Call<MiddleSchoolData>

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

    @GET("school/list/ran")
    fun getSchoolDataByRegion(
        @Query("size") size: Int = 5000,
        @Query("q") q: String
    ) : Call<SchoolData>

    @GET("school")
    fun getSchoolDetailData(
        @Query("schoolIdx") schoolIdx: Int
    ) : Call<SchoolDetail>

    @POST("review")
    fun postReview(
        @Body review:SendReview
    ): Call<Any?>
    
    @GET("review/list")
    fun getReviewList(
        @Query("schoolIdx") schoolIdx: Int,
        @Query("size") size: Int = 100
    ) : Call<Review?>

    @GET("/pick")
    fun getPickBoolean(
        @Query("schoolIdx") schoolIdx: Int
    ) : Call<Pick>

    @PATCH("/pick")
    fun PatchPick(
        @Query("schoolIdx") schoolIdx: Int
    ) : Call<Any?>
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
        @Body comment: CommentWrite
    ): Call<Any?>

    @GET("/comment/list")
    fun getComment(
        @Query("postIdx") postIdx: Int
    ): Call<Comment?>

    @PATCH("/estimate")
    fun patchEstimate(
        @Query("estimate") estimate: Int,
        @Query("schoolIdx") schoolIdx: Int
    ) : Call<Any?>

    @GET("/estimate/avg")
    fun getEstimate(
        @Query("schoolIdx") schoolIdx: Int
    ) : Call<Estimate>

    @GET("/user")
    fun getUser(): Call<User>

    @GET("user/picked")
    fun getMyPicked() : Call<SchoolData>
}