package com.example.molaschoolproject.activity

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.molaschoolproject.*
import com.example.molaschoolproject.data_type.Login
import com.example.molaschoolproject.data_type.token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    var isExistBlank = false
    lateinit var userId: EditText
    lateinit var userPw: EditText
    lateinit var btn_login: Button
    lateinit var text_signup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView(this@LoginActivity)

        text_signup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
//            loginValidation()
            val id = userId.text.toString()
            val pw = userPw.text.toString()
            val login = Login(id, pw)
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://10.80.162.195:8040/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(RetrofitService::class.java)
            service.login(login).enqueue(object :Callback<token>{
                    override fun onFailure(call: Call<token>, t: Throwable) {
                        Toast.makeText(this@LoginActivity,"로그인 실패", Toast.LENGTH_LONG).show()
//                        if(isExistBlank) {
//                        }
                    }
                    override fun onResponse(call: Call<token>, response: Response<token>) {
                        if (response.isSuccessful) {
                            App.prefs.token = response.body()?.data?.accessToken
                            Log.d("retrofitt","sp token = ${response.body()?.data?.accessToken}")
                            Toast.makeText(this@LoginActivity,"로그인 하셨습니다", Toast.LENGTH_LONG).show()
                            startActivity(
                                Intent(this@LoginActivity, MainActivity::class.java)
                            )
                        }
                    }
                })
        }
    }

//    fun loginValidation() { // 회원가입 예외처리
//        if(userId.toString().isEmpty()||userPw.toString().isEmpty()) {
//            isExistBlank = true
//        }
//    }

    fun initView(activity: Activity) {
        userId= activity.findViewById(R.id.edit_id)
        userPw = activity.findViewById(R.id.edit_pw)
        btn_login = activity.findViewById(R.id.btn_login)
        text_signup = activity.findViewById(R.id.text_signup)
    }
}