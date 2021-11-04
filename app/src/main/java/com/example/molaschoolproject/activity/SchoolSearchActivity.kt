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
import com.example.molaschoolproject.R
import com.example.molaschoolproject.RetrofitService
import com.example.molaschoolproject.data_type.SignUp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SchoolSearchActivity : AppCompatActivity() {

    lateinit var btnNext: Button
    lateinit var schoolName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_search)

        init()

        btnNext.setOnClickListener {
            Toast.makeText(this@SchoolSearchActivity, "학교 설정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            signUpSchool()
        }
    }

    private fun signUpSchool() {
        if (schoolName.text.isEmpty()) {
            Toast.makeText(this@SchoolSearchActivity, "빈칸을 채워주세요.", Toast.LENGTH_SHORT).show()
        } else {
            val schoolName = schoolName.text.toString()
            val id = intent.getStringExtra("id")
            val pw = intent.getStringExtra("pw")
            val age = intent.getIntExtra("age", 0)
            val sex = intent.getStringExtra("sex")
            val intent = Intent(this@SchoolSearchActivity, LocalSearchActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("pw", pw)
            intent.putExtra("age", age)
            intent.putExtra("sex", sex)
            intent.putExtra("schoolName", schoolName)
            startActivity(intent)
        }
    }


    fun init() {
        schoolName = findViewById(R.id.edit_school_name)
        btnNext = findViewById(R.id.btn_next)
    }
}

