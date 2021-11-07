package com.example.molaschoolproject.activity

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.R
import com.example.molaschoolproject.RetrofitService
import com.example.molaschoolproject.adapter.SchoolSearchAdapter
import com.example.molaschoolproject.data_type.SchoolData
import com.example.molaschoolproject.data_type.SchoolProfiles
import com.example.molaschoolproject.data_type.SignUp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SchoolSearchActivity : AppCompatActivity() {

    lateinit var btnNext: Button
    lateinit var schoolName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_search)

        val rvSchoolSearch = findViewById<RecyclerView>(R.id.rv_school)
        rvSchoolSearch.layoutManager =
            LinearLayoutManager(this@SchoolSearchActivity, LinearLayoutManager.VERTICAL, false)

        rvSchoolSearch.setHasFixedSize(true)

        init()

        btnNext.setOnClickListener {
            Toast.makeText(this@SchoolSearchActivity, "학교 설정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            signUpSchool()
        }


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.61.124:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val schoolSearchService = retrofit.create(RetrofitService::class.java)

        schoolSearchService.getSchoolData().enqueue(object : Callback<SchoolData> {
            override fun onResponse(call: Call<SchoolData>, response: Response<SchoolData>) {
                Log.d("Retrofitt","SchoolSearch code = ${response.code()}")
                if(response.isSuccessful){
                    val schoolSearchList = response.body()?.data
                    val schoolSearchAdapter = SchoolSearchAdapter(schoolSearchList as ArrayList<SchoolProfiles>)
                    schoolSearchAdapter.setItemClickListener(object : SchoolSearchAdapter.OnItemClickListener {
                        override fun onClick(v: View, position: Int) {
                            schoolName.setText(schoolSearchList[position].schoolName)
                            Toast.makeText(v.context,"position = ${schoolSearchList[position].schoolName}",Toast.LENGTH_SHORT).show()
                        }
                    })
                    rvSchoolSearch.adapter = schoolSearchAdapter
                }
            }

            override fun onFailure(call: Call<SchoolData>, t: Throwable) {

            }
        })


        schoolName.setOnClickListener {
            val searchService = retrofit.create(RetrofitService::class.java)
            var searchSchoolData: String = schoolName.text.toString()
            if (searchSchoolData.isEmpty())  searchSchoolData = ""
            searchSchoolData = searchSchoolData.replace(" ","")
            searchService.getSchoolDataByName(q = searchSchoolData).enqueue(object : Callback<SchoolData> {

                override fun onResponse(call: Call<SchoolData>, response: Response<SchoolData>) {
                    Log.d("Retrofitt","searchByName main code = ${response.code()}")
                    if(response.isSuccessful){
                        val schoolSearchList = response.body()?.data
                        val schoolSearchAdapter = SchoolSearchAdapter(schoolSearchList as ArrayList<SchoolProfiles>)
                        schoolSearchAdapter.setItemClickListener(object : SchoolSearchAdapter.OnItemClickListener {
                            override fun onClick(v: View, position: Int) {
                                schoolName.setText(schoolSearchList[position].schoolName) 
                                Toast.makeText(v.context,"position = ${schoolSearchList[position].schoolName}",Toast.LENGTH_SHORT).show()
                            }
                        })
                        rvSchoolSearch.adapter = schoolSearchAdapter

                    }
                }

                override fun onFailure(call: Call<SchoolData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    fun signUpSchool() {
        if (schoolName.text.isEmpty()) {
            Toast.makeText(this@SchoolSearchActivity, "빈칸을 채워주세요.", Toast.LENGTH_SHORT).show()
        } else {
            val schoolName = schoolName.text.toString()
            val id = intent.getStringExtra("id")
            val pw = intent.getStringExtra("pw")
            val age = intent.getIntExtra("age", 0)
            val sex = intent.getStringExtra("sex")
            val intent = Intent(this@SchoolSearchActivity, LocalSearchActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("pw", pw)
            intent.putExtra("age", age)
            intent.putExtra("sex", sex)
            intent.putExtra("schoolName", schoolName)
            startActivity(intent)
        }
    }

    fun init() {
        schoolName = findViewById(R.id.edit_school_name)
        btnNext = findViewById(R.id.btn_next)
    }
}

