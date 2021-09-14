package com.example.molaschoolproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class LocalSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_search)

        val btn_back: ImageView = findViewById(R.id.btn_back)

        btn_back.setOnClickListener {
            val intent = Intent(this@LocalSearchActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}