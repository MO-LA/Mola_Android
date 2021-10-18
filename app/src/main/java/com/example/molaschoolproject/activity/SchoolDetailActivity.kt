package com.example.molaschoolproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.molaschoolproject.R

class SchoolDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_detail)

        val ivBack: ImageView = findViewById(R.id.iv_back)
        ivBack.setOnClickListener { finish() }


    }
}