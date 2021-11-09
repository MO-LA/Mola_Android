package com.example.molaschoolproject.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.molaschoolproject.*
import com.example.molaschoolproject.data_type.CommunityData
import com.example.molaschoolproject.data_type.CommunityWrite
import com.example.molaschoolproject.data_type.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommunityWritingActivity : AppCompatActivity() {

    lateinit var userSchool: TextView
    lateinit var userId: TextView
    lateinit var userTitle: EditText
    lateinit var userContent: EditText
    lateinit var btnWriteFinish: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_writing)

        init(this@CommunityWritingActivity)
        communityUser()

        btnWriteFinish.setOnClickListener {
            communityPost()
        }
    }

    fun communityPost() {
        if (userTitle.text.isEmpty() || userContent.text.isEmpty()) {
            Toast.makeText(this@CommunityWritingActivity, "빈칸을 채워주세요.", Toast.LENGTH_SHORT).show()
        } else {
            val userTitle = userTitle.text.toString()
            val userContent = userContent.text.toString()

            val service = CreateRetrofit().hasTokenRetrofit()
            service.postCommunity(CommunityWrite(title = userTitle, content = userContent))
                .enqueue(object : Callback<CommunityWrite> {
                    override fun onResponse(call: Call<CommunityWrite>, response: Response<CommunityWrite>) {
                        if(response.isSuccessful) {
                            Log.d("retrofitt","post = ${App.prefs.token}")
                            Toast.makeText(this@CommunityWritingActivity, "게시물이 올라갔습니다.", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@CommunityWritingActivity, CommunityActivity::class.java)
                            startActivity(intent)
                        }
                    }
                    override fun onFailure(call: Call<CommunityWrite>, t: Throwable) {
                        Toast.makeText(this@CommunityWritingActivity, "서버와의 연결이 끊어졌습니다.", Toast.LENGTH_SHORT).show()

                    }
                })
        }

    }

    fun communityUser() {

        val service = CreateRetrofit().hasTokenRetrofit()
        service.getUser().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful) {
                    userSchool.text = response.body()?.data?.school
                    userId.text = response.body()?.data?.id
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {

            }

        })

    }

    fun init(activity: Activity) {
        userSchool = activity.findViewById(R.id.txt_school)
        userId = activity.findViewById(R.id.txt_id)
        userTitle = activity.findViewById(R.id.edit_title)
        userContent = activity.findViewById(R.id.edit_content)
        btnWriteFinish = activity.findViewById(R.id.btn_writeFinish)
    }
}