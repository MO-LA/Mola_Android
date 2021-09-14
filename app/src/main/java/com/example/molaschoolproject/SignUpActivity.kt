package com.example.molaschoolproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    lateinit var user_id: EditText
    lateinit var user_pw: EditText
    lateinit var user_age: EditText
    lateinit var user_pw_check: EditText
    lateinit var user_radiogroup: RadioGroup
    lateinit var btn_signup: Button
    lateinit var sex: String
    lateinit var btn_overlap: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initView(this@SignUpActivity)
        registerListenr()

        btn_overlap.setOnClickListener {
            overlapID(this@SignUpActivity)
        }

        user_radiogroup.setOnCheckedChangeListener { radioGroup, i ->
            val radioButton = radioGroup.findViewById<RadioButton>(i)
            sex = radioButton.text.toString()
        }
    }

    fun signup(activity: Activity) {
        if(user_pw.getText().toString().equals(user_pw_check.getText().toString())){
            val userSex = if(sex == "남") "M" else "W"
            val userId = user_id.text.toString()
            val userPassword = user_pw.text.toString()
            val userAge = user_age.text.toString().toInt()
            (application as MasterApplication).service.signup(SignUp(userId, userPassword, userAge, userSex))
                .enqueue(object : Callback<Any?> {
                    override fun onFailure(call: Call<Any?>, t: Throwable) { t.printStackTrace()
                        Toast.makeText(activity, "가입에 실패하였습니다.", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                        if (response.isSuccessful) {
                            Toast.makeText(activity, "가입에 성공하였습니다.", Toast.LENGTH_LONG).show()
                            (application as MasterApplication).createRetrofit()
                            val intent = Intent(this@SignUpActivity, SchoolSearchActivity::class.java)
                            intent.putExtra("id", userId)
                            intent.putExtra("pw", userPassword)
                            intent.putExtra("age", userAge)
                            intent.putExtra("sex", userSex)
                            startActivity(
                                intent
                            )
                        }
                    }
                })
        }
        else
            Toast.makeText(activity, "비밀번호 불일치", Toast.LENGTH_SHORT).show()
    }

    fun registerListenr() {
        btn_signup.setOnClickListener {
            signup(this@SignUpActivity)
            filter(this@SignUpActivity)
        }
    }

    fun overlapID(activity: Activity) {
        val userId = user_id.text.toString()
        (application as MasterApplication).service.overlapID(userId)
            .enqueue(object : Callback<Any?> {
                override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                    Toast.makeText(activity, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    Toast.makeText(activity, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun filter(activity: Activity) {
        user_id.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            val ps: Pattern =
                Pattern.compile("^[a-zA-Z0-9\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$")
            if (source == "" || ps.matcher(source).matches()) {
                return@InputFilter source
            }
            Toast.makeText( this, "영문, 숫자만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
            ""
        }, InputFilter.LengthFilter(10))

        user_pw.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            val ps: Pattern =
                Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{8,15}.\$\n")
            if (source == "" || ps.matcher(source).matches()) {
                return@InputFilter source
            }
            Toast.makeText( this, " 숫자, 문자, 특수문자 모두 포함하여야 합니다.", Toast.LENGTH_SHORT).show()
            ""
        }, InputFilter.LengthFilter(15))
    }

    fun initView(activity: Activity) {
        user_id = activity.findViewById(R.id.signup_edit_id)
        user_pw = activity.findViewById(R.id.signup_edit_pw)
        user_age = activity.findViewById(R.id.signup_edit_age)
        user_pw_check = activity.findViewById(R.id.signup_edit_pw_check)
        user_radiogroup = activity.findViewById(R.id.sign_radiogroup)
        btn_signup = activity.findViewById(R.id.btn_signup)
        btn_overlap = activity.findViewById(R.id.btn_overlap)
    }


}
