package com.example.molaschoolproject.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.molaschoolproject.MasterApplication
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
    lateinit var textSkip: TextView
    lateinit var schoolName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_search)

        init(this@SchoolSearchActivity)

        btnNext.setOnClickListener {
            signUpSchool(this@SchoolSearchActivity)
        }
    }

    private fun signUpSchool(activity: Activity) {
        val schoolName = schoolName.text.toString()
        val id = intent.getStringExtra("id")
        val pw = intent.getStringExtra("pw")
        val age = intent.getIntExtra("age", 0)
        val sex = intent.getStringExtra("sex")

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.80.162.195:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitService::class.java)
        service.signup(SignUp(schoolName))
            .enqueue(object : Callback<SignUp> {
                override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                    if(response.isSuccessful) {
                        Toast.makeText(this@SchoolSearchActivity, "학교 설정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SchoolSearchActivity, LocalSearchActivity::class.java)
                        intent.putExtra("id", id)
                        intent.putExtra("pw", pw)
                        intent.putExtra("age", age)
                        intent.putExtra("sex", sex)
                        intent.putExtra("schoolName", schoolName)
                        startActivity(intent)
                    }

                }
                override fun onFailure(call: Call<SignUp>, t: Throwable) {
                    Toast.makeText(this@SchoolSearchActivity, "학교 설정 실패었습니다.", Toast.LENGTH_SHORT).show()

                }
            })
    }

    fun init(activity: Activity) {
        schoolName = activity.findViewById(R.id.edit_school_name)
        btnNext = activity.findViewById(R.id.btn_next)
        textSkip = activity.findViewById(R.id.text_skip)
    }

}