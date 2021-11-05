package com.example.molaschoolproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.AuthInterceptor
import com.example.molaschoolproject.R
import com.example.molaschoolproject.RetrofitService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommunityDetailActivity : AppCompatActivity() {

    lateinit var detailTitle: TextView
    lateinit var detailTime: TextView
    lateinit var detailId: TextView
    lateinit var detailContent: TextView
    lateinit var editReview: EditText
    lateinit var imgBtnSend: ImageButton
    private lateinit var imgBtnBack: ImageButton
    lateinit var rv_review: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)

        init()
        rv_review.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rv_review.setHasFixedSize(true)

        setText()
    }

    fun communityWrite() {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://10.80.162.195:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)
    }

    fun setText() {
        detailTitle.text = intent.getStringExtra("title")
        detailTime.text = intent.getStringExtra("dateTime")
        detailId.text = intent.getStringExtra("postIdx")
        detailContent.text = intent.getStringExtra("content")

        val postIdx: Int = intent.getIntExtra("idx", 0)
        Toast.makeText(this,"postIdx = $postIdx", Toast.LENGTH_SHORT).show()
    }

    fun init() {
        detailTitle = findViewById(R.id.txt_community_detail_title)
        detailTime = findViewById(R.id.txt_community_detail_time)
        detailId= findViewById(R.id.txt_community_detail_id)
        detailContent = findViewById(R.id.txt_community_detail_content)
        editReview = findViewById(R.id.edit_review)
        imgBtnSend = findViewById(R.id.img_btn_review_send)
        rv_review = findViewById(R.id.rv_review)
    }
}