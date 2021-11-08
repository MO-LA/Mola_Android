package com.example.molaschoolproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.R
import com.example.molaschoolproject.data_type.Word
import com.example.molaschoolproject.adapter.WordAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class WordSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_search)

        val ivWordsearchBack: ImageView = findViewById(R.id.iv_wordsearch_back) // 뒤로가기 뷰
        ivWordsearchBack.setOnClickListener { finish() }

        val btnvWordsearch: BottomNavigationView = findViewById(R.id.btnv_wordsearch)
        btnvWordsearch.findViewById<View>(R.id.btnv_item_wordsearch).performClick() // WordSearchActivity 로 이동시 item wordsearch 클릭보여주기
        btnvWordsearch.findViewById<View>(R.id.btnv_item_home).setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
        btnvWordsearch.findViewById<View>(R.id.btnv_item_community).setOnClickListener{
            val intent = Intent(this, CommunityActivity::class.java)
            finish()
            startActivity(intent)
        }
        btnvWordsearch.findViewById<View>(R.id.btnv_item_mypage).setOnClickListener{
            val intent = Intent(this, MyPageActivity::class.java)
            finish()
            startActivity(intent)
        }

        val wordList = arrayListOf( // 용어 데이터 리스트
            Word("일반고", "다양한 분야에 걸쳐 일반적인 교육을 실시하는 고등학교이다.",
                "링크 : https://namu.wiki/w/%EC%9D%BC%EB%B0%98%EA%B3%84%20%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"),
            Word("자율형\n공립고", "진보한 형태의 공교육 모델을 만드는 것이 목표인 학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),
            Word("자율형\n사립고", "다양하고 특성화된 교육과정과 학사운영 등을 자율적으로 운영하는 학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),
            Word("특성화고", "특정 분야 인재 및 전문 직업인 양성을 위한 특성화 교육과정을 운영하는 학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),
            Word("특수목적고", "특수분야의 전문적인 교육을 위해 설립된 고등학교의 한 형태이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),
            Word("과학고", "과학 및 수학에 중점을 둔 특수목적 고등학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),
            Word("마이스터고", "유망분야의 특화된 예비 마이스터를 양성하는 고등학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

        )

        val rv_wordsearch: RecyclerView = findViewById(R.id.rv_wordsearch) // 용어 설명 리사이클러뷰
        rv_wordsearch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rv_wordsearch.setHasFixedSize(true)

        rv_wordsearch.adapter = WordAdapter(wordList)



    }
}