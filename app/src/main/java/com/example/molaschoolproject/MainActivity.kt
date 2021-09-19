package com.example.molaschoolproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val profileList = arrayListOf(
            SchoolProfiles("대소고", 5.0, 10, "마이스터", R.drawable.img_dgsw),
            SchoolProfiles("대소고", 5.0, 10, "마이스터", R.drawable.img_dgsw),
            SchoolProfiles("대소고", 5.0, 10, "마이스터", R.drawable.img_dgsw),
            SchoolProfiles("대소고", 5.0, 10, "마이스터", R.drawable.img_dgsw),
            SchoolProfiles("대소고", 5.0, 10, "마이스터", R.drawable.img_dgsw),
            SchoolProfiles("대소고", 5.0, 10, "마이스터", R.drawable.img_dgsw),

        )
        val rv_main = findViewById<RecyclerView>(R.id.rv_main)
        rv_main.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_main.setHasFixedSize(true)

        rv_main.adapter = ProfileAdapter(profileList)
    }
}