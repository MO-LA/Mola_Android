package com.example.molaschoolproject

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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            loginValidation()
            val id = userId.text.toString()
            val pw = userPw.text.toString()
            val login = Login(id, pw)
            (application as MasterApplication).service.login(login)
                .enqueue(object :Callback<User>{
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        if(isExistBlank) {
                            dialog("blank")
                        }
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

    fun loginValidation() { // 회원가입 예외처리
        if(userId.toString().isEmpty()||userPw.toString().isEmpty()) {
            isExistBlank = true
        }
    }

    fun dialog(type: String) { // dialog 함수
        val dialog = AlertDialog.Builder(this)

        // 작성 안한 항목이 있을 경우
        if(type.equals("blank")) {
            dialog.setTitle("로그인 실패")
            dialog.setMessage("입력란을 모두 작성해주세요")
        }

        val dialogListener = object: DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE ->
                        Log.d("dialog", "다이얼로그")
                }
            }
        }
        dialog.setPositiveButton("확인",dialogListener)
        dialog.show()
    }

    fun initView(activity: Activity) {
        userId= activity.findViewById(R.id.edit_id)
        userPw = activity.findViewById(R.id.signup_edit_pw)
        btn_login = activity.findViewById(R.id.btn_login)
        text_signup = activity.findViewById(R.id.text_signup)
    }
}