package com.example.molaschoolproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.AuthInterceptor
import com.example.molaschoolproject.R
import com.example.molaschoolproject.RetrofitService
import com.example.molaschoolproject.SchoolAssessmentBottomSheet
import com.example.molaschoolproject.adapter.CommentAdapter
import com.example.molaschoolproject.data_type.Review
import com.example.molaschoolproject.data_type.SendReview
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SchoolDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_detail)

        val ivBack: ImageView = findViewById(R.id.iv_back)
        ivBack.setOnClickListener { finish() }

        val rv_comment: RecyclerView = findViewById(R.id.rv_comment)
        rv_comment.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_comment.setHasFixedSize(true)

        val ivSchoolDetailMyPick: ImageView = findViewById(R.id.iv_schooldetail_mypick)

        val tvSchooldetailTitle: TextView = findViewById(R.id.tv_schooldetail_title)
        val tvSchooldetailSchoolName: TextView = findViewById(R.id.school_name)

        tvSchooldetailTitle.text = intent.getStringExtra("schoolName")
        tvSchooldetailSchoolName.text = intent.getStringExtra("schoolName")

        val tvSchooldetailEstimate: TextView = findViewById(R.id.tv_schooldetail_estimate)
        tvSchooldetailEstimate.text = intent.getStringExtra("estimate")


        val schoolIdx: Int = intent.getIntExtra("schoolIdx",0)
        Toast.makeText(this,"schoolIdx = $schoolIdx",Toast.LENGTH_SHORT).show()
        var booleanPick: Boolean = false
        ivSchoolDetailMyPick.setOnClickListener {
            if (booleanPick == false) {
                ivSchoolDetailMyPick.setImageResource(R.drawable.ic_pick_true)
                booleanPick = true
            }
            else {
                ivSchoolDetailMyPick.setImageResource(R.drawable.ic_pick_false)
                booleanPick = false
            }
        }

        val editComment: EditText = findViewById(R.id.edit_comment)

        val ibtnCommentSend: ImageButton = findViewById(R.id.ibtn_comment_send)

        val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()
        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://10.80.162.195:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)
        ibtnCommentSend.setOnClickListener{
            var content = editComment.text.toString()
            service.postReview(SendReview(content, schoolIdx)).enqueue(object : Callback<Any?> {
                override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                    Log.d("retrofitt","review post retrofit code = ${response.code()}")
                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    Log.d("retrofitt","review post retrofit false")
                }
            })
            val restartIntent = Intent(this,SchoolDetailActivity::class.java)
            restartIntent.putExtra("schoolName",intent.getStringExtra("schoolName"))
            restartIntent.putExtra("schoolIdx",intent.getIntExtra("schoolIdx",0))
            finish()
            startActivity(restartIntent)
        }


        service.getReviewList(schoolIdx).enqueue(object : Callback<Review?> {
            override fun onResponse(call: Call<Review?>, response: Response<Review?>) {
                Log.d("Retrofitt","reviewlist code = ${response.code()}")
                val reviewList = response.body()?.data
                Log.d("Retrofitt","reviewList = ${response.body()?.data}")
                rv_comment.adapter = reviewList?.let { CommentAdapter(it) }
            }

            override fun onFailure(call: Call<Review?>, t: Throwable) {
                Log.d("Retrofitt","reviewList = false")
            }
        })





        val fabAssessment: FloatingActionButton = findViewById(R.id.fab_schooldetail)

        fabAssessment.setOnClickListener{
            val bottomSheet = SchoolAssessmentBottomSheet()
            bottomSheet.show(supportFragmentManager,bottomSheet.tag)
        }
    }
}