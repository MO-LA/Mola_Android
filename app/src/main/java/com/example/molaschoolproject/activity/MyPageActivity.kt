package com.example.molaschoolproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.molaschoolproject.R
import com.example.molaschoolproject.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyPageActivity : AppCompatActivity() {

    lateinit var userid: TextView
    lateinit var usershool: TextView
    lateinit var btnMyPick: LinearLayout
    init {
        userid = findViewById(R.id.text_name)
        usershool = findViewById(R.id.text_school)
        btnMyPick = findViewById(R.id.layout_mypick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        myPage()

        btnMyPick.setOnClickListener {
            val intent = Intent(this@MyPageActivity, MyPickActivity::class.java)
            startActivity(intent)
        }
    }

    fun myPage() {
        val userid = userid.text.toString()
        val userschool = usershool.text.toString()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.80.162.195:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitService::class.java)
        service.myPage(userid, userschool)
            .enqueue(object : Callback<Any?> {
                override fun onResponse(call: Call<Any?>, response: Response<Any?>) {

                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {

                }
            })
    }
}