package com.example.molaschoolproject.activity

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.widget.*
import androidx.core.view.isEmpty
import com.example.molaschoolproject.R
import com.example.molaschoolproject.RetrofitService
import com.example.molaschoolproject.data_type.Overlap
import com.example.molaschoolproject.data_type.OverlapData
import com.example.molaschoolproject.data_type.SignUp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    var isExistBlank = false
    lateinit var userId: EditText
    lateinit var userPw: EditText
    lateinit var userAge: EditText
    lateinit var userPwCheck: EditText
    lateinit var userRadiogroup: RadioGroup
    lateinit var btnSignup: Button
    lateinit var sex: String
    lateinit var btnOverlap: Button
    lateinit var imgBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initView(this@SignUpActivity)

//        registerListener() // 회원 가입 버튼 실행 함수

        btnSignup.setOnClickListener {
//            registerValidation()
            signUp(this@SignUpActivity)
//            filter(this@SignUpActivity)
        }

        imgBack.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        btnOverlap.setOnClickListener { // 중복확인 버튼
            overlapID(this@SignUpActivity) // 중복을 확인해주는 함수 실행
        }
        // 남, 여 체크박스
        userRadiogroup.setOnCheckedChangeListener { radioGroup, i ->
            val radioButton = radioGroup.findViewById<RadioButton>(i)
            sex = radioButton.text.toString()
        }
    }

    // 회원가입 함수
    fun signUp(activity: Activity) {

        if (userId.text.isEmpty() ||
            userPw.text.isEmpty() ||
            userPwCheck.text.isEmpty() ||
            userAge.text.isEmpty() ||
            userRadiogroup.isEmpty()
        ) {
            Toast.makeText(this@SignUpActivity, "빈칸을 채워주세요.", Toast.LENGTH_SHORT).show()
        } else {
            if (userPw.getText().toString().equals(userPwCheck.getText().toString())) {
                val userSex = if (sex == "남") "M" else "W"
                val userId = userId.text.toString()
                val userPassword = userPw.text.toString()
                val userAge = userAge.text.toString().toInt()
                if (!Pattern.matches("^[a-zA-Z0-9]*\$", userId)) {
                    Toast.makeText(this, "아이디는 영문 & 숫자만 가능합니다.", Toast.LENGTH_SHORT).show()
                    return
                }
                else if (!Pattern.matches("^[a-zA-Z0-9]*\$", userPassword)) {
                    Toast.makeText(this, "비밀번호는 영문 & 숫자만 가능합니다.", Toast.LENGTH_LONG).show()
                    return
                }
                else {
                    Toast.makeText(this@SignUpActivity, "회원정보가 설정되었습니다!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@SignUpActivity, SchoolSearchActivity::class.java)
                    intent.putExtra("id", userId)
                    intent.putExtra("pw", userPassword)
                    intent.putExtra("age", userAge)
                    intent.putExtra("sex", userSex)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this@SignUpActivity, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun overlapID(activity: Activity) { // 아이디 중복체크
        val userId = userId.text.toString()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.80.162.195:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitService::class.java)
        service.overlapID(Overlap(userId))
            .enqueue(object : Callback<OverlapData> {
                override fun onResponse(call: Call<OverlapData>, response: Response<OverlapData>) {
                    var data: Boolean = response.body()!!.data
                    Log.d("retrofitt", "data = $data")
                    if (data == true) {
                        Toast.makeText(activity, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<OverlapData>, t: Throwable) {
                    Toast.makeText(activity, "서버에 연결이 끊어졌습니다.", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun initView(activity: Activity) {
        userId = activity.findViewById(R.id.signup_edit_id)
        userPw = activity.findViewById(R.id.signup_edit_pw)
        userAge = activity.findViewById(R.id.signup_edit_age)
        userPwCheck = activity.findViewById(R.id.signup_edit_pw_check)
        userRadiogroup = activity.findViewById(R.id.sign_radiogroup)
        btnSignup = activity.findViewById(R.id.btn_signup)
        btnOverlap = activity.findViewById(R.id.btn_overlap)
        imgBack = activity.findViewById(R.id.iv_back)
    }


}
