package com.example.molaschoolproject.activity

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import com.example.molaschoolproject.CreateRetrofit
import com.example.molaschoolproject.R
import com.example.molaschoolproject.data_type.Overlap
import com.example.molaschoolproject.data_type.OverlapData
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
    lateinit var imgBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initView(this@SignUpActivity)

        signupMinLenth()

        btnSignup.setOnClickListener {
            signUp(this@SignUpActivity)
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
        if (userId.text.isEmpty() || userPw.text.isEmpty() ||
            userPwCheck.text.isEmpty() || userAge.text.isEmpty() || userRadiogroup.isEmpty()) {
            Toast.makeText(this@SignUpActivity, "빈칸을 채워주세요.", Toast.LENGTH_SHORT).show()
        }
        else {
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
                else if(userAge == 0) {
                    Toast.makeText(this, "나이는 0살 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                }
                else if(userId.length < 6 || userPassword.length < 6){
                    Toast.makeText(this, "ID 및 비밀번호는 6 ~ 12자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "회원정보가 설정되었습니다!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, SchoolSearchActivity::class.java)
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

        val service = CreateRetrofit().noHeaderRetrofit()
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

    fun signupMinLenth() {
        userId.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (userId.length() < 8) {
                    var dialog = AlertDialog.Builder(this)
                    dialog.setMessage("ID는 6 ~ 12자 이상이어야 합니다.")

                    var dialog_listener = object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog?.dismiss()
                        }
                    }
                    dialog.setPositiveButton("ok", dialog_listener)
                    dialog.show()
                }
            }
        })

        userPw.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (userId.length() < 8) {
                    var dialog = AlertDialog.Builder(this)
                    dialog.setMessage("PW는 6 ~ 15자 이상이어야 합니다.")

                    var dialog_listener = object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog?.dismiss()
                        }
                    }
                    dialog.setPositiveButton("ok", dialog_listener)
                    dialog.show()
                }
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
