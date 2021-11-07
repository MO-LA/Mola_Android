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
import com.example.molaschoolproject.data_type.*
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
        Toast.makeText(this,"context = ${intent.getStringExtra("context")}",Toast.LENGTH_SHORT).show()
        ivBack.setOnClickListener {
            if (intent.getStringExtra("context") == "M"){
                val intent: Intent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            }
            else {
                val intent: Intent = Intent(this, MyPickActivity::class.java)
                finish()
                startActivity(intent)
            }
        }

        val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()
        val retrofit: Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://10.80.162.195:8040/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        val rv_comment: RecyclerView = findViewById(R.id.rv_comment)
        rv_comment.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_comment.setHasFixedSize(true)

        val ivSchoolDetailMyPick: ImageView = findViewById(R.id.iv_schooldetail_mypick)

        val tvSchooldetailTitle: TextView = findViewById(R.id.tv_schooldetail_title)
        val tvSchooldetailSchoolName: TextView = findViewById(R.id.school_name)
        val tvHomepage: TextView = findViewById(R.id.tv_homepage)
        val tvSchooldetailContents: TextView = findViewById(R.id.tv_schooldetail_contents)

        val tvSchooldetailEstimate: TextView = findViewById(R.id.tv_schooldetail_estimate)
        tvSchooldetailEstimate.text = intent.getStringExtra("estimate")

        val ivStarOne: ImageView = findViewById(R.id.star_one)
        val ivStarTwo: ImageView = findViewById(R.id.star_two)
        val ivStarThree: ImageView = findViewById(R.id.star_three)
        val ivStarFour: ImageView = findViewById(R.id.star_four)
        val ivStarFive: ImageView = findViewById(R.id.star_five)

        val estimateDouble:Double = intent.getDoubleExtra("estimateDouble",0.0)

        if (estimateDouble < 1)

        else if (estimateDouble >= 5) {
            ivStarOne.setImageResource(R.drawable.ic_baseline_star_24)
            ivStarTwo.setImageResource(R.drawable.ic_baseline_star_24)
            ivStarThree.setImageResource(R.drawable.ic_baseline_star_24)
            ivStarFour.setImageResource(R.drawable.ic_baseline_star_24)
            ivStarFive.setImageResource(R.drawable.ic_baseline_star_24)
        }
        else if (estimateDouble >= 4) {
            ivStarOne.setImageResource(R.drawable.ic_baseline_star_24)
            ivStarTwo.setImageResource(R.drawable.ic_baseline_star_24)
            ivStarThree.setImageResource(R.drawable.ic_baseline_star_24)
            ivStarFour.setImageResource(R.drawable.ic_baseline_star_24)
        }
        else if (estimateDouble >= 3) {
            ivStarOne.setImageResource(R.drawable.ic_baseline_star_24)
            ivStarTwo.setImageResource(R.drawable.ic_baseline_star_24)
            ivStarThree.setImageResource(R.drawable.ic_baseline_star_24)
        }
        else if (estimateDouble >= 2) {
            ivStarOne.setImageResource(R.drawable.ic_baseline_star_24)
            ivStarTwo.setImageResource(R.drawable.ic_baseline_star_24)
        }
        else if (estimateDouble >= 1) ivStarOne.setImageResource(R.drawable.ic_baseline_star_24)



        val schoolIdx: Int = intent.getIntExtra("schoolIdx",0)
        Toast.makeText(this,"schoolIdx = $schoolIdx",Toast.LENGTH_SHORT).show()

        var pick: Boolean
        service.getPickBoolean(schoolIdx = schoolIdx).enqueue(object : Callback<Pick> {
            override fun onResponse(call: Call<Pick>, response: Response<Pick>) {
                Log.d("Retrofitt","pick code = ${response.code()}")
                if (response.isSuccessful) {
                    pick = response.body()!!.data
                    Log.d("Retrofitt","getPick code = ${response.code()} pick = $pick")
                    if (pick == true) ivSchoolDetailMyPick.setImageResource(R.drawable.ic_pick_true)
                    else ivSchoolDetailMyPick.setImageResource(R.drawable.ic_pick_false)
                }
            }

            override fun onFailure(call: Call<Pick>, t: Throwable) {
            }
        })

        ivSchoolDetailMyPick.setOnClickListener {
            service.PatchPick(schoolIdx = schoolIdx).enqueue(object : Callback<Any?> {
                override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                    Log.d("Retrofitt","Patch Pick code = ${response.code()}")
                    if (response.isSuccessful) {
                        service.getPickBoolean(schoolIdx = schoolIdx).enqueue(object : Callback<Pick> {
                            override fun onResponse(call: Call<Pick>, response: Response<Pick>) {
                                Log.d("Retrofitt","pick code = ${response.code()}")
                                if (response.isSuccessful) {
                                    pick = response.body()!!.data
                                    Log.d("Retrofittt","pick = $pick")
                                    if (pick == true) ivSchoolDetailMyPick.setImageResource(R.drawable.ic_pick_true)
                                    else ivSchoolDetailMyPick.setImageResource(R.drawable.ic_pick_false)
                                }
                            }
                            override fun onFailure(call: Call<Pick>, t: Throwable) {
                                Log.d("Retrofitt","pick false")
                            }
                        })
                    }
                }
                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    Log.d("Retrofitt","Patch Pick false")
                }
            })



        }

        var schoolDetailData: SchoolDetailData
        service.getSchoolDetailData(schoolIdx = schoolIdx).enqueue(object : Callback<SchoolDetail> {
            override fun onResponse(call: Call<SchoolDetail>, response: Response<SchoolDetail>) {
                Log.d("Retrofitt","SchoolDetail code = ${response.code()}")
                if(response.isSuccessful) {
                    schoolDetailData = response.body()?.data!!
                    tvSchooldetailTitle.text = schoolDetailData.schoolName
                    tvSchooldetailSchoolName.text = schoolDetailData.schoolName

                    tvHomepage.text = schoolDetailData.homePage

                    var schoolDetailContents: String
                    if (schoolDetailData.administrativeOfficeTel == null) schoolDetailData.administrativeOfficeTel = "없음"
                    if (schoolDetailData.staffroomTel == null) schoolDetailData.staffroomTel = "없음"

                    if (schoolDetailData.schoolKind == "GENERAL") schoolDetailData.schoolKind = "일반고"
                    else if (schoolDetailData.schoolKind == "SPECIAL_PURPOSE") schoolDetailData.schoolKind = "특수목적고"
                    else if (schoolDetailData.schoolKind == "AUTONOMOUS") schoolDetailData.schoolKind = "자율고"
                    else if (schoolDetailData.schoolKind == "SPECIALIZED") schoolDetailData.schoolKind = "특성화고"

                    if (schoolDetailData.fond == "PRIVATE") schoolDetailData.fond = "사립"
                    else if (schoolDetailData.fond == "PUBLIC") schoolDetailData.fond = "공립"
                    else if (schoolDetailData.fond == "NATIONAL") schoolDetailData.fond = "국립"

                    if (schoolDetailData.fondType == "INDEPENDENCE") schoolDetailData.fondType = "단설"
                    else if (schoolDetailData.fondType == "ESTABLISH") schoolDetailData.fondType = "병설"
                    else if (schoolDetailData.fondType == "ACCESSORIES") schoolDetailData.fondType = "부속"

                    if (schoolDetailData.genderCheck == "M") schoolDetailData.genderCheck = "남고"
                    else if (schoolDetailData.genderCheck == "W") schoolDetailData.genderCheck = "여고"
                    else if (schoolDetailData.genderCheck == "MW") schoolDetailData.genderCheck = "남녀공학"

                    schoolDetailContents = "행정실 전화번호 : ${schoolDetailData.administrativeOfficeTel} \n" +
                            "교무실 전화번호 : ${schoolDetailData.staffroomTel} \n" +
                            "도로명 주소 : ${schoolDetailData.roadNameAddress} \n" +
                            "학교 유형 : ${schoolDetailData.schoolKind} \n" +
                            "설립 구분 : ${schoolDetailData.fond} \n" +
                            "설립 유형 : ${schoolDetailData.fondType} \n" +
                            "학교 성별 : ${schoolDetailData.genderCheck} \n" +
                            "남녀 성비 : 남 ${schoolDetailData.maleSum.toString()}명 여 ${schoolDetailData.femaleSum}명"
                    tvSchooldetailContents.text = schoolDetailContents
                }
            }

            override fun onFailure(call: Call<SchoolDetail>, t: Throwable) {
                Log.d("Retrofitt","SchoolDetailFalse")
            }
        })

        val editComment: EditText = findViewById(R.id.edit_comment)

        val ibtnCommentSend: ImageButton = findViewById(R.id.ibtn_comment_send)

        ibtnCommentSend.setOnClickListener{
            var content = editComment.text.toString()
            service.postReview(SendReview(content, schoolIdx)).enqueue(object : Callback<Any?> {
                override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                    Log.d("retrofitt","review post retrofit code = ${response.code()}")
                    val restartIntent = Intent(this@SchoolDetailActivity,SchoolDetailActivity::class.java)
                    restartIntent.putExtra("schoolIdx",intent.getIntExtra("schoolIdx",0))
                    finish()
                    startActivity(restartIntent)
                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    Log.d("retrofitt","review post retrofit false")
                }
            })

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
            var args: Bundle = Bundle()
            args.putInt("schoolIdx",schoolIdx)
            bottomSheet.arguments = args
            bottomSheet.show(supportFragmentManager,bottomSheet.tag,)
        }
    }

    override fun onBackPressed() {
        if (intent.getStringExtra("context") == "M"){
            val intent: Intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
        else {
            val intent: Intent = Intent(this, MyPickActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}