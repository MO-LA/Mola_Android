package com.example.molaschoolproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView

class WordSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_search)

        val ivWordsearchBack: ImageView = findViewById(R.id.iv_wordsearch_back)
        ivWordsearchBack.setOnClickListener { finish() }







        val btnvWordsearch: BottomNavigationView = findViewById(R.id.btnv_wordsearch)
        btnvWordsearch.findViewById<View>(R.id.btnv_item_wordsearch).performClick() // WordSearchActivity 로 이동시 item wordsearch 클릭보여주기
    }
}