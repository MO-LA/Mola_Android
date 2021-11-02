package com.example.molaschoolproject.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.molaschoolproject.MasterApplication
import com.example.molaschoolproject.R
import com.example.molaschoolproject.data_type.SignUp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val localName = localName.text.toString()
        (application as MasterApplication).service.signup(SignUp(localName))
            .enqueue(object : Callback<SignUp> {
                override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                    if (response.isSuccessful) {
                        //회원가입 -> 학교등록에서 받아오던 정보 getExtra로 받아고 보내기
                        val intent = Intent(this@LocalSearchActivity, MainActivity::class.java)
                        Toast.makeText(activity, "지역 등록에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<SignUp>, t: Throwable) {
                    if (isExistBlank) {
                        dialog("blank")
                    } else {
                        dialog("failure")
                    }
                }
            })
    }

    fun dialog(type: String) { // dialog 함수
        val dialog = AlertDialog.Builder(this)

        // 작성 안한 항목이 있을 경우
        if (type.equals("blank")) {
            dialog.setTitle("지역 등록 실패")
            dialog.setMessage("입력란을 모두 작성해주세요")
        }
        if (type.equals("failure")) {
            dialog.setTitle("지역 등록 실패")
            dialog.setMessage("지역 등록에 실패하였습니다.")
        }
    }

    fun init(activity: Activity) {
        localName = activity.findViewById(R.id.edit_local_name)
        btnNext = activity.findViewById(R.id.btn_next)
        textSkip = activity.findViewById(R.id.text_skip)
        imgBack = activity.findViewById(R.id.img_back)
    }
}
