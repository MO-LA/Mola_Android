package com.example.molaschoolproject.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.AuthInterceptor
import com.example.molaschoolproject.R
import com.example.molaschoolproject.RetrofitService
import com.example.molaschoolproject.adapter.CommunityAdapter
import com.example.molaschoolproject.data_type.Community
import com.example.molaschoolproject.data_type.CommunityData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommunityActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

//        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

        val btnWrite: FloatingActionButton = findViewById(R.id.fab_write)
        val rvCommunity = findViewById<RecyclerView>(R.id.rv_community)
        rvCommunity.layoutManager =
            LinearLayoutManager(this@CommunityActivity, LinearLayoutManager.VERTICAL, false)
        rvCommunity.setHasFixedSize(true)

        btnWrite.setOnClickListener {
            val intent = Intent(this@CommunityActivity, CommunityWritingActivity::class.java)
            startActivity(intent)
        }

        val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://192.168.61.124/:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        service.getCommunity().enqueue(object : Callback<CommunityData> {
            override fun onResponse(call: Call<CommunityData>, response: Response<CommunityData>) {
                Log.d("Retrofitt","comm code = ${response.code()}")
                if (response.isSuccessful) {
                    val communityList = response.body()?.data
                    Log.d("Retrofitt", "mainList = ${response.body()?.data}")
                    rvCommunity.adapter = CommunityAdapter(communityList as ArrayList<Community>)
                }
            }

            override fun onFailure(call: Call<CommunityData>, t: Throwable) {
                Log.d("Retrofitt","main False")
            }

        })
    }
}