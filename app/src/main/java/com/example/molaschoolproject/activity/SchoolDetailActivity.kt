package com.example.molaschoolproject.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.R
import com.example.molaschoolproject.SchoolAssessmentBottomSheet
import com.example.molaschoolproject.adapter.CommentAdapter
import com.example.molaschoolproject.data_type.Comment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SchoolDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_detail)

        val ivBack: ImageView = findViewById(R.id.iv_back)
        ivBack.setOnClickListener { finish() }

        val rv_comment: RecyclerView = findViewById(R.id.rv_comment)

        val ivSchoolDetailMyPick: ImageView = findViewById(R.id.iv_schooldetail_mypick)

        val tvSchooldetailTitle: TextView = findViewById(R.id.tv_schooldetail_title)
        val tvSchooldetailSchoolName: TextView = findViewById(R.id.school_name)

        tvSchooldetailTitle.text = intent.getStringExtra("schoolName")
        tvSchooldetailSchoolName.text = intent.getStringExtra("schoolName")

        val tvSchooldetailEstimate: TextView = findViewById(R.id.tv_schooldetail_estimate)
        tvSchooldetailEstimate.text = "5.0"

        val schoolIdx: Int = intent.getIntExtra("schoolIdx",0)

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


        val commentList = arrayListOf(
            Comment("홍길동","대소고","대소고 좋습니다"),
            Comment("조주영","머소고","대소고 싫습니다"),
            Comment("서민교","대소마고","대소고 좋습니다"),
            Comment("허새찬","대구소프트웨어고","대소고 좋아요"),
            Comment("이민욱","대구소프트웨어마이스터고","대소고 좋습니다"),
            Comment("서민교","대소마고","대소고 좋습니다"),
            Comment("허새찬","대구소프트웨어고","대소고 좋아요"),
            Comment("이민욱","대구소프트웨어마이스터고","대소고 좋습니다"),
            Comment("서민교","대소마고","대소고 좋습니다"),
            Comment("허새찬","대구소프트웨어고","대소고 좋아요"),
            Comment("이민욱","대구소프트웨어마이스터고","대소고 좋습니다"),
        )

        rv_comment.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_comment.setHasFixedSize(true)

        rv_comment.adapter = CommentAdapter(commentList)

        val fabAssessment: FloatingActionButton = findViewById(R.id.fab_schooldetail)

        fabAssessment.setOnClickListener{
            val bottomSheet = SchoolAssessmentBottomSheet()
            bottomSheet.show(supportFragmentManager,bottomSheet.tag)
        }
    }
}