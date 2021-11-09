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
import com.example.molaschoolproject.CreateRetrofit
import com.example.molaschoolproject.R
import com.example.molaschoolproject.RetrofitService
import com.example.molaschoolproject.adapter.SchoolSearchAdapter
import com.example.molaschoolproject.data_type.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class SchoolSearchActivity : AppCompatActivity() {

    lateinit var btnNext: Button
    lateinit var schoolName: EditText
    var schoolNameString: String? = null
    lateinit var schoolNameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_search)

        val rvSchoolSearch = findViewById<RecyclerView>(R.id.rv_school)
        rvSchoolSearch.layoutManager =
            LinearLayoutManager(this@SchoolSearchActivity, LinearLayoutManager.VERTICAL, false)

        rvSchoolSearch.setHasFixedSize(true)

        init()

        btnNext.setOnClickListener {
            signUpSchool()
        }

        schoolName.setOnClickListener {
            val searchService = CreateRetrofit().noHeaderRetrofit()
            var searchSchoolData: String = schoolName.text.toString()

            if (searchSchoolData.isEmpty()) searchSchoolData = ""
            searchSchoolData = searchSchoolData.replace(" ", "")
            searchService.getMiddleSchoolDataByName(q = searchSchoolData).enqueue(object : Callback<MiddleSchoolData> {
                    override fun onResponse(call: Call<MiddleSchoolData>, response: Response<MiddleSchoolData>) {
                        Log.d("Retrofitt", "searchByName main code = ${response.code()}")
                        if (response.isSuccessful) {
                            val schoolSearchList = response.body()?.data
                            val schoolSearchAdapter =
                                SchoolSearchAdapter(schoolSearchList as ArrayList<MiddleSchool>)
                            schoolSearchAdapter.setItemClickListener(object : SchoolSearchAdapter.OnItemClickListener {
                                override fun onClick(v: View, position: Int) {
                                    schoolNameTextView.text = schoolSearchList[position].name
                                    schoolNameString = schoolSearchList[position].name.toString()
                                    Toast.makeText(
                                        v.context,
                                        "position = ${schoolSearchList[position].name}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                            rvSchoolSearch.adapter = schoolSearchAdapter
                        }
                    }

                    override fun onFailure(call: Call<MiddleSchoolData>, t: Throwable) {
                    }
                })
        }
    }


    fun signUpSchool() {
        if (schoolNameString == null) {
            Toast.makeText(this@SchoolSearchActivity, "빈칸을 채워주세요.", Toast.LENGTH_SHORT).show()
        } else {
            val userSchool = schoolNameString
            Toast.makeText(this@SchoolSearchActivity,"userSchool = $userSchool",Toast.LENGTH_SHORT).show()
            if (!Pattern.matches("^[가-힣]*\$", userSchool)) {
                Toast.makeText(this@SchoolSearchActivity, "학교검색은 한글만 가능합니다.", Toast.LENGTH_SHORT).show()
                return
            }
            else {
                Toast.makeText(this@SchoolSearchActivity, "학교 설정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                val id = intent.getStringExtra("id")
                val pw = intent.getStringExtra("pw")
                val age = intent.getIntExtra("age", 0)
                val sex = intent.getStringExtra("sex")

                val intent = Intent(this@SchoolSearchActivity, LocalSearchActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("pw", pw)
                intent.putExtra("age", age)
                intent.putExtra("sex", sex)
                intent.putExtra("schoolName", userSchool)
                startActivity(intent)
            }
        }
    }

    fun init() {
        schoolNameTextView = findViewById(R.id.select_school)
        schoolName = findViewById(R.id.edit_school_name)
        btnNext = findViewById(R.id.btn_next)
    }
}