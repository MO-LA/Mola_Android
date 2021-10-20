package com.example.molaschoolproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.example.molaschoolproject.R

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        val mypick:LinearLayout = findViewById(R.id.layout_mypick)

        mypick.setOnClickListener {
            val intent = Intent(this@MyPageActivity, MyPickActivity::class.java)
            startActivity(intent)
        }
    }
}