package com.example.molaschoolproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.AuthInterceptor
import com.example.molaschoolproject.CreateRetrofit
import com.example.molaschoolproject.R
import com.example.molaschoolproject.RetrofitService
import com.example.molaschoolproject.adapter.ProfileAdapter
import com.example.molaschoolproject.data_type.SchoolData
import com.example.molaschoolproject.data_type.SchoolProfiles
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyPickActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pick)

        val ivMyPickBack: ImageView = findViewById(R.id.iv_mypick_back)
        ivMyPickBack.setOnClickListener { finish() }

        val rvMyPick = findViewById<RecyclerView>(R.id.rv_mypick) // 메인 리사이클러뷰
        rvMyPick.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMyPick.setHasFixedSize(true)

        val service = CreateRetrofit().hasTokenRetrofit()

        service.getMyPicked().enqueue(object : Callback<SchoolData> {
            override fun onResponse(call: Call<SchoolData>, response: Response<SchoolData>) {
                if (response.isSuccessful) {
                    val profileList = response.body()?.data
                    Log.d("Retrofitt","mainList = ${response.body()?.data}")
                    rvMyPick.adapter = ProfileAdapter(profileList as ArrayList<SchoolProfiles>)
                }
            }

            override fun onFailure(call: Call<SchoolData>, t: Throwable) {

            }
        })
    }
}