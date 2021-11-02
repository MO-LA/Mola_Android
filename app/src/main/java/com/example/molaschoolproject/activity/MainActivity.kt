package com.example.molaschoolproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.*
import com.example.molaschoolproject.adapter.ProfileAdapter
import com.example.molaschoolproject.data_type.SchoolData
import com.example.molaschoolproject.data_type.SchoolProfiles
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val schoolcategory:TextView = findViewById(R.id.tv_schoolcatecory)
        val rvMain = findViewById<RecyclerView>(R.id.rv_main) // 메인 리사이클러뷰
        rvMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMain.setHasFixedSize(true)


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.80.162.195:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        service.getSchoolData().enqueue(object: retrofit2.Callback<SchoolData>{
            override fun onResponse(call: Call<SchoolData>, response: Response<SchoolData>) {
                Log.d("Retrofitt","main code = ${response.code()}")
                if(response.isSuccessful) {
                    val profileList = response.body()?.data
                    Log.d("Retrofitt","mainList = ${response.body()?.data}")
                    rvMain.adapter = ProfileAdapter(profileList as ArrayList<SchoolProfiles>)
                }
            }

            override fun onFailure(call: Call<SchoolData>, t: Throwable) {
                Log.d("Retrofitt","main False")
            }
        })




        schoolcategory.setOnClickListener{ // 학교 유형 선택 카테고리
            val bottomSheet = SchoolCategoryBottomSheet()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)

            bottomSheet.setOnClickedListener(object : SchoolCategoryBottomSheet.textClickListener {
                override fun onClicked(typetext: String) {
                    schoolcategory.text = typetext
                }
            })
        }

        val searchMain: EditText = findViewById(R.id.edit_search_main) // 메인 검색창
        val searchText: String = searchMain.text.toString()

        val btnvMain = findViewById<BottomNavigationView>(R.id.btnv_main)
        btnvMain.findViewById<View>(R.id.btnv_item_wordsearch).setOnClickListener{
            val intent = Intent(this@MainActivity, WordSearchActivity::class.java)
            startActivity(intent)
        }


    }
}