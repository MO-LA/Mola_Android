package com.example.molaschoolproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchoolSearchActivity : AppCompatActivity() {

    val btn_next: Button = findViewById(R.id.btn_next)
    val text_skip: TextView = findViewById(R.id.text_skip)
    val schoolName: EditText = findViewById(R.id.edit_school_name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_search)

        registerListener()
        skipListener()
    }

    fun signUpSchool(activity: Activity) {
        val schoolName = schoolName.text.toString()
        (application as MasterApplication).service.signup(SignUp(schoolName))
            .enqueue(object : Callback<Any?> {
                override fun onResponse(call: Call<Any?>, response: Response<Any?>) {

                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    
                }
            })
    }

    fun registerListener() { // 회원가입 버튼 함수
        btn_next.setOnClickListener {
            signUpSchool(this@SchoolSearchActivity)
        }
    }

    fun skipListener() {
        text_skip.setOnClickListener {
            var intent = Intent(this@SchoolSearchActivity, LocalSearchActivity::class.java)
            startActivity(intent)
        }
    }

}