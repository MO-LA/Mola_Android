package com.example.molaschoolproject.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.molaschoolproject.AuthInterceptor
import com.example.molaschoolproject.R
import com.example.molaschoolproject.RetrofitService
import com.example.molaschoolproject.data_type.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyPageActivity : AppCompatActivity() {

    lateinit var userSchool: TextView
    lateinit var userId: TextView
    lateinit var btnMypick: Button
    lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        init()
        setTextMypage()

        btnMypick.setOnClickListener {
            val intent = Intent(this@MyPageActivity, MyPickActivity::class.java)
            startActivity(intent)
        }
    }

    fun setTextMypage() {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://10.80.162.195:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)
        service.getUser().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Log.d("retrofitt", "Success")
                    userSchool.text = response.body()?.data?.school
                    userId.text = response.body()?.data?.id
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("retrofitt", "Fail")
            }

        })
    }

    fun logout() {
        btnLogout.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("로그아웃을 하시겠습니까?")
            dialog.setMessage("저희 SAFE FARM을 이용해주셔서 감사합니다.")

            fun toast_p() {
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

            var dialog_listener = object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE ->
                            toast_p()
                    }
                }
            }
            dialog.setPositiveButton("YES", dialog_listener)
            dialog.setNegativeButton("NO", null)
            dialog.show()
        }
    }

    fun init() {
        userSchool = findViewById(R.id.txt_mypage_school)
        userId = findViewById(R.id.txt_mypage_id)
        btnMypick = findViewById(R.id.layout_btn_mypick)
        btnLogout = findViewById(R.id.layout_btn_logout)
    }

}