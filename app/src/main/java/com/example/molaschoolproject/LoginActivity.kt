package com.example.molaschoolproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edit_id: EditText = findViewById(R.id.edit_id)
        val edit_pw: EditText = findViewById(R.id.edit_pw)
        val btn_login: Button = findViewById(R.id.btn_login)
        val text_signup: TextView = findViewById(R.id.text_signup)

        text_signup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            val id = edit_id.text.toString()
            val pw = edit_pw.text.toString()
            val login = Login(id, pw)
            (application as MasterApplication).service.login(login)
                .enqueue(object :Callback<User>{
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "로그인에 실패하였습니다.", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            (application as MasterApplication).createRetrofit()
                            Toast.makeText(this@LoginActivity,"로그인 하셨습니다", Toast.LENGTH_LONG).show()
                            startActivity(
                                Intent(this@LoginActivity, LocalSearchActivity::class.java)
                            )
                        }
                    }
                })
        }
    }
}