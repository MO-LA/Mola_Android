package com.example.molaschoolproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.*
import com.example.molaschoolproject.adapter.ProfileAdapter
import com.example.molaschoolproject.data_type.SchoolProfiles
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val schoolcategory:TextView = findViewById(R.id.tv_schoolcatecory)

        val profileList = arrayListOf( // 더미데이터
            SchoolProfiles("대소고", 5.0, 10,"남녀공학" ,"마이스터", "구지"),
            SchoolProfiles("대소고", 5.0, 10,"남녀공학" ,"마이스터","굳이"),
            SchoolProfiles("대소고", 5.0, 10,"남녀공학" ,"마이스터","대구"),
            SchoolProfiles("대소고", 5.0, 10,"남녀공학" ,"마이스터", "구지"),
            SchoolProfiles("대소고", 5.0, 10,"남녀공학" ,"마이스터","굳이"),
            SchoolProfiles("대소고", 5.0, 10,"남녀공학" ,"마이스터","대구"),
            SchoolProfiles("대소고", 5.0, 10,"남녀공학" ,"마이스터", "구지"),
            SchoolProfiles("대소고", 5.0, 10,"남녀공학" ,"마이스터","굳이"),
            SchoolProfiles("대소고", 5.0, 10,"남녀공학" ,"마이스터","대구"),
            SchoolProfiles("대소고", 5.0, 10,"남녀공학" ,"마이스터", "구지"),
            SchoolProfiles("대소고", 5.0, 10,"남녀공학" ,"마이스터","굳이"),
            SchoolProfiles("대소고", 5.0, 10,"남녀공학" ,"마이스터","대구"),
            
        )
        val rvMain = findViewById<RecyclerView>(R.id.rv_main) // 메인 리사이클러뷰
        rvMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMain.setHasFixedSize(true)

        rvMain.adapter = ProfileAdapter(profileList)

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