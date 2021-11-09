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



        val wordList = arrayListOf( // 용어 데이터 리스트
            Word("일반고", "다양한 분야에 걸쳐 일반적인 교육을 실시하는 고등학교이다.",
                "나무위키","https://namu.wiki/w/%EC%9D%BC%EB%B0%98%EA%B3%84%20%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"),

            Word("자율형\n공립고", "진보한 형태의 공교육 모델을 만드는 것이 목표인 학교이다.",
                "나무위키","https://namu.wiki/w/%EC%9E%90%EC%9C%A8%ED%98%95%20%EA%B3%B5%EB%A6%BD%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"),

            Word("자율형\n사립고", "다양하고 특성화된 교육과정과 학사운영 등을 자율적으로 운영하는 학교이다.",
                "나무위키","https://namu.wiki/w/%EC%9E%90%EC%9C%A8%ED%98%95%20%EC%82%AC%EB%A6%BD%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"),

            Word("특성화고", "특정 분야 인재 및 전문 직업인 양성을 위한 특성화 교육과정을 운영하는 학교이다.",
                "나무위키","https://namu.wiki/w/%ED%8A%B9%EC%84%B1%ED%99%94%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90?from=%ED%8A%B9%EC%84%B1%ED%99%94%EA %B3%A0"),

            Word("특수\n목적고", "특수분야의 전문적인 교육을 위해 설립된 고등학교의 한 형태이다.",
                "나무위키","https://namu.wiki/w/%ED%8A%B9%EC%88%98%EB%AA%A9%EC%A0%81%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"),

            Word("과학고", "과학 및 수학에 중점을 둔 특수목적 고등학교이다.",
                "나무위키","https://namu.wiki/w/%EA%B3%BC%ED%95%99%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90?from=%EA%B3%BC%ED%95%99%EA%B3%A0"),

            Word("마이스터고", "유망분야의 특화된 예비 마이스터를 양성하는 고등학교이다.",
                "나무위키","https://namu.wiki/w/%EB%A7%88%EC%9D%B4%EC%8A%A4%ED%84%B0%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"),

            Word("체육고", "체육에 소질이 있는 학생들을 전문적으로 교육시키기 위한 특수목적 고등학교이다.",
                "나무위키","https://namu.wiki/w/%EC%B2%B4%EC%9C%A1%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"),

            Word("외국어고", "외국어에 능통한 선도적 인재 양성을 목표로 하는 특수목적 고등학교이다.",
                "나무위키","https://namu.wiki/w/%EC%99%B8%EA%B5%AD%EC%96%B4%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"),

            Word("예고", "음악, 미술, 연극 등 대한민국의 예술인재를 양성하는데 목적을 둔 고등학교이다.",
                "나무위키","https://namu.wiki/w/%EC%98%88%EC%88%A0%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"),

            Word("농업고", "작물재배, 농산물 가공, 농업서비스와 연계된 6차산업을 활용한 농업전문 학교이다.",
                "나무위키","https://namu.wiki/w/%ED%8A%B9%EC%84%B1%ED%99%94%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90/%EB%86%8D%EC%83%9D%EB%AA%85%EC%82%B0%EC%97%85%EA%B3%84%EC%97%B4?from=%EB%86%8D%EC%97%85%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"),

            Word("공고", "고등 보통 교육 및 공업에 관한 전문 지식을 가르치는 실업 고등학교이다.",
                "나무위키","https://namu.wiki/w/%ED%8A%B9%EC%84%B1%ED%99%94%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90/%EA%B3%B5%EC%97%85%EA%B3%84%EC%97%B4?from=%EA%B3%B5%EC%97%85%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"),

            Word("수산고", "수산 및 해운업에 관한 교육을 하는 고등학교이다.",
                "나무위키","https://namu.wiki/w/%ED%8A%B9%EC%84%B1%ED%99%94%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90/%EC%88%98%EC%82%B0%C2%B7%ED%95%B4%EC%9A%B4%EA%B3%84%EC%97%B4?from=%EC%88%98%EC%82%B0%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"),

            Word("국립 학교", "나라에서 세워 직접 관리ㆍ운영하는 학교이다.",
                "나무위키","https://namu.wiki/w/%EA%B5%AD%EB%A6%BD%ED%95%99%EA%B5%90"),

            Word("사립 학교", "개인 또는 사법인이 설립하여 경영하는 학교이다.",
                "나무위키","https://namu.wiki/w/%EC%82%AC%EB%A6%BD%ED%95%99%EA%B5%90"),

            Word("공립 학교", "지방 자치 단체가 지방비로 세워서 운영하는 학교이다.",
                "나무위키","https://namu.wiki/w/%EA%B3%B5%EB%A6%BD%ED%95%99%EA%B5%90"),

            Word("부속 학교", "교육 연구 및 교원 양성 기관의 실습을 위하여 부설한 학교이다.",
                "네이버 사전","https://ko.dict.naver.com/#/entry/koko/2084db3378e84f60806dc0fe214fdd87"),

            Word("단설 학교", "하나만을 설치한 학교이다.",
                "네이버 사전","https://ko.dict.naver.com/#/entry/koko/d6b898ffbb0a4f9bbf87f3969df24878"),

            Word("병설 학교", "교육 지역 실정에 맞추어 초등학교, 중학교, 고등학교의 교육 기관 가운데 두 가지 이상을 아울러 한곳에 세운 학교이다.",
                "네이버 사전","https://ko.dict.naver.com/#/entry/koko/19a5b5a03ee84cff9495bafb49f842db"),

        )

        val rv_wordsearch: RecyclerView = findViewById(R.id.rv_wordsearch) // 용어 설명 리사이클러뷰
        rv_wordsearch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rv_wordsearch.setHasFixedSize(true)
        rv_wordsearch.adapter = WordAdapter(wordList)

    }
}