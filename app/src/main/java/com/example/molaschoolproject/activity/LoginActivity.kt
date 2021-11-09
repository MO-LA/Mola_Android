package com.example.molaschoolproject.activity

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.BackgroundColorSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
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
        
        val content = SpannableString(text_signup.text.toString())
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        text_signup.text = content

        text_signup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            if(userId.text.isEmpty() || userPw.text.isEmpty()) {
                Toast.makeText(this@LoginActivity, "빈칸을 다 채워주세요.", Toast.LENGTH_LONG)
            } else {
                val id = userId.text.toString()
                val pw = userPw.text.toString()
                val login = Login(id, pw)

                val service = CreateRetrofit().noHeaderRetrofit()
                service?.login(login)?.enqueue(object : Callback<token> {
                    override fun onFailure(call: Call<token>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "로그인 실패", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<token>, response: Response<token>) {
                        if (response.isSuccessful) {
                            App.prefs.token = response.body()?.data?.accessToken
                            Log.d("retrofitt", "sp token = ${App.prefs.token}")
                            Toast.makeText(this@LoginActivity, "로그인 하셨습니다", Toast.LENGTH_LONG).show()
                            startActivity(
                                Intent(this@LoginActivity, MainActivity::class.java)
                            )
                        }
                    }
                })
            }
        }
    }

    fun initView(activity: Activity) {
        userId= activity.findViewById(R.id.edit_id)
        userPw = activity.findViewById(R.id.edit_pw)
        btn_login = activity.findViewById(R.id.btn_login)
        text_signup = activity.findViewById(R.id.text_signup)
    }
}