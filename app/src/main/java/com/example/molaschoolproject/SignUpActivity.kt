package com.example.molaschoolproject

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initView(this@SignUpActivity)
        registerListener() // 회원 가입 버튼 실행 함수

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
        if(userPw.getText().toString().equals(userPwCheck.getText().toString())){
            val userSex = if(sex == "남") "M" else "W"
            val userId =userId.text.toString()
            val userPassword = userPw.text.toString()
            val userAge = userAge.text.toString().toInt()
            (application as MasterApplication).service.signup(SignUp(userId, userPassword, userAge, userSex))
                .enqueue(object : Callback<Any?> {
                    override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                        if (response.isSuccessful) {
                            Toast.makeText(activity, "가입에 성공하였습니다.", Toast.LENGTH_LONG).show()
                            (application as MasterApplication).createRetrofit()
                            val intent = Intent(this@SignUpActivity, SchoolSearchActivity::class.java)
                            intent.putExtra("id", userId)
                            intent.putExtra("pw", userPassword)
                            intent.putExtra("age", userAge)
                            intent.putExtra("sex", userSex)
                            startActivity(intent)
                        }
                    }
                    override fun onFailure(call: Call<Any?>, t: Throwable) { t.printStackTrace()
                        if(isExistBlank) {
                            dialog("blank")
                        }
                        else {
                            dialog("not same")
                        }
                    }
                })
        }
    }

    fun registerListener() { // 회원가입 버튼 함수
        btnSignup.setOnClickListener {
            signUp(this@SignUpActivity)
            filter(this@SignUpActivity)
            registerValidation()
        }
    }

    fun registerValidation() { // 회원가입 예외처리
        if(userId.toString().isEmpty()||userPw.toString().isEmpty()||userPwCheck.toString().isEmpty()||
            userAge.toString().isEmpty()||userRadiogroup.toString().isEmpty()) {
             isExistBlank = true
        }
    }

    fun overlapID(activity: Activity) { // 아이디 중복체크
        val userId =userId.text.toString()
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

    fun dialog(type: String) { // dialog 함수
        val dialog = AlertDialog.Builder(this)

        // 작성 안한 항목이 있을 경우
        if(type.equals("blank")) {
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("입력란을 모두 작성해주세요")
        }
        // 입력한 비밀번호가 다를 경우
        else if(type.equals("not same")) {
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("비밀번호가 다릅니다")
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

    fun filter(activity: Activity) { // 회원가입 정규식
        userId.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            val ps: Pattern =
                Pattern.compile("^[a-zA-Z0-9\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$")
            if (source == "" || ps.matcher(source).matches()) {
                return@InputFilter source
            }
            Toast.makeText( this, "영문, 숫자만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
            ""
        }, InputFilter.LengthFilter(10))

        userPw.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            val ps: Pattern =
                Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{8,15}.\$\n")
            if (source == "" || ps.matcher(source).matches()) {
                return@InputFilter source
            }
            Toast.makeText( this, " 숫자, 문자, 특수문자 모두 포함하여야 합니다.", Toast.LENGTH_SHORT).show()
            ""
        }, InputFilter.LengthFilter(15))

        userAge.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            val ps: Pattern =
                Pattern.compile("^(?=.*[0-9]).{1,3}.\$\n")
            if (source == "" || ps.matcher(source).matches()) {
                return@InputFilter source
            }
            Toast.makeText( this, " 숫자만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
            ""
        }, InputFilter.LengthFilter(3))
    }

    fun initView(activity: Activity) {
        userId= activity.findViewById(R.id.signup_edit_id)
        userPw = activity.findViewById(R.id.signup_edit_pw)
        userAge = activity.findViewById(R.id.signup_edit_age)
        userPwCheck = activity.findViewById(R.id.signup_edit_pw_check)
        userRadiogroup = activity.findViewById(R.id.sign_radiogroup)
        btnSignup = activity.findViewById(R.id.btn_signup)
        btnOverlap = activity.findViewById(R.id.btn_overlap)
    }
}
