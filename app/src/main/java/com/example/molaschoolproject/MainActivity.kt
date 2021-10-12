package com.example.molaschoolproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val schoolcategory:TextView = findViewById(R.id.tv_schoolcatecory)

        val profileList = arrayListOf( // 더미데이터
            SchoolProfiles("대소고", 5.0, 10, "마이스터"),
            SchoolProfiles("대소고", 5.0, 10, "마이스터"),
            SchoolProfiles("대소고", 5.0, 10, "마이스터"),
            SchoolProfiles("대소고", 5.0, 10, "마이스터"),
            SchoolProfiles("대소고", 5.0, 10, "마이스터"),
            SchoolProfiles("대소고", 5.0, 10, "마이스터")
        )
        val rvMain = findViewById<RecyclerView>(R.id.rv_main)
        rvMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMain.setHasFixedSize(true)

        rvMain.adapter = ProfileAdapter(profileList)

        schoolcategory.setOnClickListener{
            val bottomSheet = SchoolCategoryBottomSheet()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)

            bottomSheet.setOnClickedListener(object :SchoolCategoryBottomSheet.textClickListener {
                override fun onClicked(typetext: String) {
                    schoolcategory.text = typetext
                }
            })
        }
    }
}