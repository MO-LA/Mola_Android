package com.example.molaschoolproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SchoolSearchActivity : AppCompatActivity() {

    lateinit var btnNext: Button
    lateinit var textSkip: TextView
    lateinit var schoolName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_search)

        init(this@SchoolSearchActivity)
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
        btnNext.setOnClickListener {
            signUpSchool(this@SchoolSearchActivity)
        }
    }

    fun skipListener() {
        textSkip.setOnClickListener {
            var intent = Intent(this@SchoolSearchActivity, LocalSearchActivity::class.java)
            startActivity(intent)
        }
    }

    fun init(activity: Activity) {
        schoolName = activity.findViewById(R.id.edit_school_name)
        btnNext = activity.findViewById(R.id.btn_next)
        textSkip = activity.findViewById(R.id.text_skip)
    }

}