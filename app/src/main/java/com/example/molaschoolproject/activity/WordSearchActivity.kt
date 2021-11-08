package com.example.molaschoolproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
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

//        val url = "https://namu.wiki/w/%EC%9D%BC%EB%B0%98%EA%B3%84%20%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"
//        val content = SpannableString(.text.toString())
//        content.setSpan(URLSpan(url), 0, content.length, 0)
//        text_signup.setMovementMethod(LinkMovementMethod.getInstance())
//        text_signup.setText(content)



        val wordList = arrayListOf( // 용어 데이터 리스트
            Word("일반고", "다양한 분야에 걸쳐 일반적인 교육을 실시하는 고등학교이다.",
                "" ),

            Word("자율형\n공립고", "진보한 형태의 공교육 모델을 만드는 것이 목표인 학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("자율형\n사립고", "다양하고 특성화된 교육과정과 학사운영 등을 자율적으로 운영하는 학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("특성화고", "특정 분야 인재 및 전문 직업인 양성을 위한 특성화 교육과정을 운영하는 학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("특수\n목적고", "특수분야의 전문적인 교육을 위해 설립된 고등학교의 한 형태이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("과학고", "과학 및 수학에 중점을 둔 특수목적 고등학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("마이스터고", "유망분야의 특화된 예비 마이스터를 양성하는 고등학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("체육고", "체육에 소질이 있는 학생들을 전문적으로 교육시키기 위한 특수목적 고등학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("외국어고", "외국어에 능통한 선도적 인재 양성을 목표로 하는 특수목적 고등학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("예고", "음악, 미술, 연극 등 대한민국의 예술인재를 양성하는데 목적을 둔 고등학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("농고", "작물재배, 농산물 가공, 농업서비스와 연계된 6차산업을 활용한 농업전문 학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("공고", "고등 보통 교육 및 공업에 관한 전문 지식을 가르치는 실업 고등학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("수산고", "수산 및 해운업에 관한 교육을 하는 고등학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("국립 학교", "나라에서 세워 직접 관리ㆍ운영하는 학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("사랍 학교", "개인 또는 사법인이 설립하여 경영하는 학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("부속 학교", "교육 연구 및 교원 양성 기관의 실습을 위하여 부설한 학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("단설 학교", "하나만을 설치한 학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("공립 학교", "지방 자치 단체가 지방비로 세워서 운영하는 학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

            Word("병설 학교", "교육 지역 실정에 맞추어 초등학교, 중학교, 고등학교의 교육 기관 가운데 두 가지 이상을 아울러 한곳에 세운 학교이다.",
                "https://www.youtube.com/watch?v=9DJWt3crhWE"),

        )

        val rv_wordsearch: RecyclerView = findViewById(R.id.rv_wordsearch) // 용어 설명 리사이클러뷰
        rv_wordsearch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rv_wordsearch.setHasFixedSize(true)
        rv_wordsearch.adapter = WordAdapter(wordList)

    }
}