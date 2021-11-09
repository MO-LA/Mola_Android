package com.example.molaschoolproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.*
import com.example.molaschoolproject.adapter.CommentAdapter
import com.example.molaschoolproject.data_type.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.round

class SchoolDetailActivity : AppCompatActivity() {
    private var schoolIdx: Int = 0

    lateinit var ivSchoolDetailMyPick: ImageView // 찜 버튼

    lateinit var tvSchooldetailTitle: TextView // Title 학교 이름
    lateinit var tvSchooldetailSchoolName: TextView // 상세정보 학교 이름
    lateinit var tvHomepage: TextView // 상세정보 학교 주소
    lateinit var tvSchooldetailContents: TextView // 상세정보 학교 상세설명
    lateinit var tvSchooldetailEstimate: TextView // 상세정보 학교 평점

    lateinit var ivStarOne: ImageView
    lateinit var ivStarTwo: ImageView
    lateinit var ivStarThree: ImageView
    lateinit var ivStarFour: ImageView
    lateinit var ivStarFive: ImageView

    lateinit var editComment: EditText // 리뷰작성란
    lateinit var ibtnCommentSend: ImageButton // 리뷰 작성 커밋하기

    lateinit var fabAssessment: FloatingActionButton

    private var reviewList: List<ReviewList>? = null // 댓글 리스트

    private var pick: Boolean = false

    private lateinit var schoolDetailData: SchoolDetailData

    private val service = CreateRetrofit().hasTokenRetrofit() // 레트로핏 서비스 생성
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_detail)

        schoolIdx = intent.getIntExtra("schoolIdx",0)

        init() // 뷰 연결

        val ivBack: ImageView = findViewById(R.id.iv_back) // 뒤로가기 버튼

        ivBack.setOnClickListener {
            if (intent.getStringExtra("context") == "M"){
                val intent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            }
            else {
                val intent = Intent(this, MyPickActivity::class.java)
                finish()
                startActivity(intent)
            }
        } // 뒤로가기 클릭 이벤트

        val rvComment: RecyclerView = findViewById(R.id.rv_comment) // 리뷰 리사이클러뷰
        rvComment.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvComment.setHasFixedSize(true)

        schoolDetailDataSettings(rvComment) // 학교 상세정보 가져오기

        ivSchoolDetailMyPick.setOnClickListener { // 평점 남기기
            patchPick()
        }

        ibtnCommentSend.setOnClickListener{
            postReview(rvComment)
        }

        fabAssessment.setOnClickListener{
            val bottomSheet = SchoolAssessmentBottomSheet()
            var args: Bundle = Bundle()
            args.putInt("schoolIdx",schoolIdx)
            bottomSheet.arguments = args
            bottomSheet.show(supportFragmentManager,bottomSheet.tag)

            bottomSheet.setOnClickedListener(object : SchoolAssessmentBottomSheet.buttonClickListener {
                override fun onClicked(estimateScore: Int) {
                    patchEstimate(estimateScore)
                }
            })
        }
    }

    fun init() {
        ivSchoolDetailMyPick = findViewById(R.id.iv_schooldetail_mypick)

        tvSchooldetailTitle = findViewById(R.id.tv_schooldetail_title)
        tvSchooldetailSchoolName = findViewById(R.id.school_name)
        tvHomepage = findViewById(R.id.tv_homepage)
        tvSchooldetailContents = findViewById(R.id.tv_schooldetail_contents)

        tvSchooldetailEstimate = findViewById(R.id.tv_schooldetail_estimate)

        ivStarOne = findViewById(R.id.star_one)
        ivStarTwo = findViewById(R.id.star_two)
        ivStarThree = findViewById(R.id.star_three)
        ivStarFour = findViewById(R.id.star_four)
        ivStarFive = findViewById(R.id.star_five)

        editComment = findViewById(R.id.edit_comment)
        ibtnCommentSend = findViewById(R.id.ibtn_comment_send)

        fabAssessment = findViewById(R.id.fab_schooldetail)
    }

    fun getReviewList(rvComment:RecyclerView) {
        service.getReviewList(schoolIdx).enqueue(object : Callback<Review?> {
            override fun onResponse(call: Call<Review?>, response: Response<Review?>) {
                Log.d("Retrofitt","reviewlist code = ${response.code()}")
                reviewList = (response.body()?.data) as List<ReviewList>
                Log.d("Retrofitt","reviewList = ${response.body()?.data}")
                rvComment.adapter = CommentAdapter(reviewList as ArrayList<ReviewList>)
            }

            override fun onFailure(call: Call<Review?>, t: Throwable) {
                Log.d("Retrofitt","reviewList = false")
            }
        })
    }

    fun getEstimate() {
        service.getEstimate(schoolIdx = schoolIdx).enqueue(object : Callback<Estimate> {
            var estimate: Double? = 0.0
            override fun onResponse(call: Call<Estimate>, response: Response<Estimate>) {
                Log.d("Retrofitt","Estimate code = ${response.code()}")
                if (response.isSuccessful) {
                    estimate = response.body()?.data!!
                    Log.d("Retrofitt","Estimate = ${estimate}")
                    estimate = round((estimate!! * 10)) / 10

                    tvSchooldetailEstimate.text = estimate.toString()

                    if (estimate!! < 1) {
                        ivStarOne.setImageResource(R.drawable.ic_baseline_star_border_24)
                        ivStarTwo.setImageResource(R.drawable.ic_baseline_star_border_24)
                        ivStarThree.setImageResource(R.drawable.ic_baseline_star_border_24)
                        ivStarFour.setImageResource(R.drawable.ic_baseline_star_border_24)
                        ivStarFive.setImageResource(R.drawable.ic_baseline_star_border_24)
                    }
                    else if (estimate!! >= 5) {
                        ivStarOne.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarTwo.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarThree.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarFour.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarFive.setImageResource(R.drawable.ic_baseline_star_24)
                    }
                    else if (estimate!! >= 4) {
                        ivStarOne.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarTwo.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarThree.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarFour.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarFive.setImageResource(R.drawable.ic_baseline_star_border_24)
                    }
                    else if (estimate!! >= 3) {
                        ivStarOne.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarTwo.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarThree.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarFour.setImageResource(R.drawable.ic_baseline_star_border_24)
                        ivStarFive.setImageResource(R.drawable.ic_baseline_star_border_24)
                    }
                    else if (estimate!! >= 2) {
                        ivStarOne.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarTwo.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarThree.setImageResource(R.drawable.ic_baseline_star_border_24)
                        ivStarFour.setImageResource(R.drawable.ic_baseline_star_border_24)
                        ivStarFive.setImageResource(R.drawable.ic_baseline_star_border_24)
                    }
                    else if (estimate!! >= 1) {
                        ivStarOne.setImageResource(R.drawable.ic_baseline_star_24)
                        ivStarTwo.setImageResource(R.drawable.ic_baseline_star_border_24)
                        ivStarThree.setImageResource(R.drawable.ic_baseline_star_border_24)
                        ivStarFour.setImageResource(R.drawable.ic_baseline_star_border_24)
                        ivStarFive.setImageResource(R.drawable.ic_baseline_star_border_24)
                    }
                }
            }

            override fun onFailure(call: Call<Estimate>, t: Throwable) {
                Log.d("Retrofitt","Estimate false")
            }
        })
    }

    fun getPick() {
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
    }

    fun patchPick() {
        service.PatchPick(schoolIdx = schoolIdx).enqueue(object : Callback<Any?> {
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                Log.d("Retrofitt","Patch Pick code = ${response.code()}")
                if (response.isSuccessful) {
                    getPick()
                }
            }
            override fun onFailure(call: Call<Any?>, t: Throwable) {
                Log.d("Retrofitt","Patch Pick false")
            }
        })

    }

    fun getSchoolDetailData() {
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
    }

    fun postReview(rvComment: RecyclerView) {
        if (editComment.text.isEmpty()) {
            Toast.makeText(this@SchoolDetailActivity,"내용을 작성해 주세요", Toast.LENGTH_SHORT).show()
        }
        else {
            var content = editComment.text.toString()

            service.postReview(SendReview(content, schoolIdx)).enqueue(object : Callback<Any?> {
                override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                    Log.d("retrofitt","review post retrofit code = ${response.code()}")
                    if (response.isSuccessful) {
                        editComment.text = null
                        getReviewList(rvComment)
                    }

                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    Log.d("retrofitt","review post retrofit false")
                }
            })
        }
    }

    fun patchEstimate(estimateScore: Int) {
        var estimate: Double? = 0.0
        if (estimateScore != 0) {
            service.patchEstimate(estimate = estimateScore,schoolIdx = schoolIdx).enqueue(object : Callback<Any?> {
                override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                    Log.d("Retrofitt","별점 입력 code = ${response.code()}")
                    if(response.isSuccessful) {
                        getEstimate()
                    }
                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    Log.d("Retrofitt","별점 입력 실패")
                }
            })
        }
        else {
            Toast.makeText(this@SchoolDetailActivity,"별점을 입력 후에 완료를 눌러 주세요",Toast.LENGTH_SHORT).show()
        }

    }

    fun schoolDetailDataSettings(rvComment: RecyclerView){
        getReviewList(rvComment) // 학교 리뷰 불러오기

        getEstimate() // 평점 받아오기

        getPick() // 찜 여부 불러오기

        getSchoolDetailData() // 학교 상세 데이터 가져오기
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