package com.example.molaschoolproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class WordSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_search)

        val ivWordsearchBack: ImageView = findViewById(R.id.iv_wordsearch_back) // 뒤로가기 뷰
        ivWordsearchBack.setOnClickListener { finish() }

        val btnvWordsearch: BottomNavigationView = findViewById(R.id.btnv_wordsearch)
        btnvWordsearch.findViewById<View>(R.id.btnv_item_wordsearch).performClick() // WordSearchActivity 로 이동시 item wordsearch 클릭보여주기

        val wordList = arrayListOf( // 용어 데이터 리스트
            Word("초등학교", "초등학생들이 다니는 학교 입니다."),
            Word("중학교", "중학생들이 다니는 학교 입니다."),
            Word("고등학교", "고등학생들이 다니는 학교 입니다."),
            Word("초등학교", "초등학생들이 다니는 학교 입니다."),
            Word("중학교", "중학생들이 다니는 학교 입니다."),
            Word("고등학교", "고등학생들이 다니는 학교 입니다."),
            Word("초등학교", "초등학생들이 다니는 학교 입니다."),
            Word("중학교", "중학생들이 다니는 학교 입니다."),
            Word("고등학교", "고등학생들이 다니는 학교 입니다.")
        )

        val rv_wordsearch: RecyclerView = findViewById(R.id.rv_wordsearch) // 용어 설명 리사이클러뷰
        rv_wordsearch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rv_wordsearch.setHasFixedSize(true)

        rv_wordsearch.adapter = WordAdapter(wordList)



    }
}