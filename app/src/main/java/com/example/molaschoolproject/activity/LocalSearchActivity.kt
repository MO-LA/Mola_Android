package com.example.molaschoolproject.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.molaschoolproject.MasterApplication
import com.example.molaschoolproject.R
import com.example.molaschoolproject.RetrofitService
import com.example.molaschoolproject.data_type.SignUp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocalSearchActivity : AppCompatActivity() {

    var isExistBlank = false
    lateinit var btnNext: Button
    lateinit var textSkip: TextView
    lateinit var localName: EditText
    lateinit var imgBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_search)

        init(this@LocalSearchActivity)

        imgBack.setOnClickListener {
            val intent = Intent(this@LocalSearchActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    fun signUpLocal(activity: Activity) {
        val id = intent.getStringExtra("id")
        val pw = intent.getStringExtra("pw")
        val age = intent.getIntExtra("age", 0)
        val sex = intent.getStringExtra("sex")
        val schoolName = intent.getStringExtra("schoolName")
        val localName = localName.text.toString()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.80.162.195:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitService::class.java)
        service.signup(SignUp(id, pw, age, sex, schoolName, localName))
            .enqueue(object : Callback<SignUp> {
                override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                    if (response.isSuccessful) {
                        //회원가입 -> 학교등록에서 받아오던 정보 getExtra로 받아고 보내기
                        Toast.makeText(this@LocalSearchActivity, "지역 등록에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LocalSearchActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<SignUp>, t: Throwable) {
                    Toast.makeText(this@LocalSearchActivity, "지역 등록에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                }
            })
    }


    fun init(activity: Activity) {
        localName = activity.findViewById(R.id.edit_local_name)
        btnNext = activity.findViewById(R.id.btn_next)
        textSkip = activity.findViewById(R.id.text_skip)
        imgBack = activity.findViewById(R.id.img_back)
    }
}
