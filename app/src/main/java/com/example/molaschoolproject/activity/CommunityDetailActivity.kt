package com.example.molaschoolproject.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.AuthInterceptor
import com.example.molaschoolproject.CreateRetrofit
import com.example.molaschoolproject.R
import com.example.molaschoolproject.RetrofitService
import com.example.molaschoolproject.adapter.ReviewAdapter
import com.example.molaschoolproject.data_type.Comment
import com.example.molaschoolproject.data_type.CommentWrite
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommunityDetailActivity : AppCompatActivity() {

    lateinit var detailTitle: TextView
    lateinit var detailTime: TextView
    lateinit var detailId: TextView
    lateinit var detailContent: TextView
    lateinit var editReview: EditText
    lateinit var imgBtnSend: ImageButton
    lateinit var imgBtnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)

        init()

        val rvReview = findViewById<RecyclerView>(R.id.rv_review)
        rvReview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvReview.setHasFixedSize(true)

        setText()

        val postIdx: Int = intent.getIntExtra("idx", 0)

        imgBtnBack.setOnClickListener {
            val intent = Intent(this, CommunityActivity::class.java)
            finish()
            startActivity(intent)
        }


        val commentService = CreateRetrofit().hasTokenRetrofit()

        imgBtnSend.setOnClickListener {
            if (editReview.text.isEmpty()) {
                Toast.makeText(this@CommunityDetailActivity, "빈칸을 채워주세요.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var reviewContent = editReview.text.toString()
                commentService.postComment(CommentWrite(postIdx, reviewContent))
                    .enqueue(object : Callback<Any?> {
                        override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                            Log.d("retrofitt", "comment post retrofit code = ${response.code()}")
                            if(response.isSuccessful) {
                                val getCommentService = CreateRetrofit().hasTokenRetrofit()
                                getCommentService.getComment(postIdx).enqueue(object : Callback<Comment?> {
                                    override fun onResponse(call: Call<Comment?>, response: Response<Comment?>) {
                                        Log.d("Retrofitt", "get comment code = ${response.code()}")
                                        val commentList = response.body()?.data
                                        rvReview.adapter = commentList?.let { ReviewAdapter(it) }
                                    }

                                    override fun onFailure(call: Call<Comment?>, t: Throwable) {
                                        Log.d("retrofitt", "get comment retrofit false")
                                    }
                                })
                            }

                        }
                        override fun onFailure(call: Call<Any?>, t: Throwable) {
                            Log.d("retrofitt", "comment post retrofit false")
                        }
                    })
                editReview.text = null
            }
        }
        val getCommentService = CreateRetrofit().hasTokenRetrofit()
        getCommentService.getComment(postIdx).enqueue(object : Callback<Comment?> {
            override fun onResponse(call: Call<Comment?>, response: Response<Comment?>) {
                Log.d("Retrofitt", "get comment code = ${response.code()}")
                val commentList = response.body()?.data
                rvReview.adapter = commentList?.let { ReviewAdapter(it) }
            }

            override fun onFailure(call: Call<Comment?>, t: Throwable) {
                Log.d("retrofitt", "get comment retrofit false")
            }
        })
    }

    fun setText() {
        detailTitle.text = intent.getStringExtra("title")
        detailTime.text = intent.getStringExtra("dateTime")
        detailId.text = intent.getStringExtra("id")
        detailContent.text = intent.getStringExtra("content")
    }

    override fun onBackPressed() {
        val intent: Intent = Intent(this, CommunityActivity::class.java)
        finish()
        startActivity(intent)
    }

    fun init() {
        detailTitle = findViewById(R.id.txt_community_detail_title)
        detailTime = findViewById(R.id.txt_community_detail_time)
        detailId = findViewById(R.id.txt_community_detail_id)
        detailContent = findViewById(R.id.txt_community_detail_content)
        editReview = findViewById(R.id.edit_review)
        imgBtnSend = findViewById(R.id.img_btn_review_send)
        imgBtnBack = findViewById(R.id.img_detail_back)
    }


}