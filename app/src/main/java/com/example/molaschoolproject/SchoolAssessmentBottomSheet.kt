package com.example.molaschoolproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SchoolAssessmentBottomSheet() : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.schoolassessment_dialog,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var args: Bundle? = arguments
        var schoolIdx: Int = args?.getInt("schoolIdx") ?: 0

        Toast.makeText(context,"schoolIdx = $schoolIdx",Toast.LENGTH_SHORT).show()

        var estimateScore: Int = 0

        val starOne = view?.findViewById<ImageView>(R.id.assessment_star_one)
        val starTwo = view?.findViewById<ImageView>(R.id.assessment_star_two)
        val starThree = view?.findViewById<ImageView>(R.id.assessment_star_three)
        val starFour = view?.findViewById<ImageView>(R.id.assessment_star_four)
        val starFive = view?.findViewById<ImageView>(R.id.assessment_star_five)

        starOne?.setOnClickListener {
            estimateScore = 1
            starOne?.setImageResource(R.drawable.ic_baseline_star_24)
            starTwo?.setImageResource(R.drawable.ic_baseline_star_border_24)
            starThree?.setImageResource(R.drawable.ic_baseline_star_border_24)
            starFour?.setImageResource(R.drawable.ic_baseline_star_border_24)
            starFive?.setImageResource(R.drawable.ic_baseline_star_border_24)
        }
        starTwo?.setOnClickListener {
            estimateScore = 2
            starOne?.setImageResource(R.drawable.ic_baseline_star_24)
            starTwo?.setImageResource(R.drawable.ic_baseline_star_24)
            starThree?.setImageResource(R.drawable.ic_baseline_star_border_24)
            starFour?.setImageResource(R.drawable.ic_baseline_star_border_24)
            starFive?.setImageResource(R.drawable.ic_baseline_star_border_24)
        }
        starThree?.setOnClickListener {
            estimateScore = 3
            starOne?.setImageResource(R.drawable.ic_baseline_star_24)
            starTwo?.setImageResource(R.drawable.ic_baseline_star_24)
            starThree?.setImageResource(R.drawable.ic_baseline_star_24)
            starFour?.setImageResource(R.drawable.ic_baseline_star_border_24)
            starFive?.setImageResource(R.drawable.ic_baseline_star_border_24)
        }
        starFour?.setOnClickListener {
            estimateScore = 4
            starOne?.setImageResource(R.drawable.ic_baseline_star_24)
            starTwo?.setImageResource(R.drawable.ic_baseline_star_24)
            starThree?.setImageResource(R.drawable.ic_baseline_star_24)
            starFour?.setImageResource(R.drawable.ic_baseline_star_24)
            starFive?.setImageResource(R.drawable.ic_baseline_star_border_24)
        }
        starFive?.setOnClickListener {
            estimateScore = 5
            starOne?.setImageResource(R.drawable.ic_baseline_star_24)
            starTwo?.setImageResource(R.drawable.ic_baseline_star_24)
            starThree?.setImageResource(R.drawable.ic_baseline_star_24)
            starFour?.setImageResource(R.drawable.ic_baseline_star_24)
            starFive?.setImageResource(R.drawable.ic_baseline_star_24)
        }

        view?.findViewById<Button>(R.id.btn_assessment_confirm)?.setOnClickListener{
            onClickedListener.onClicked(estimateScore)
            dismiss()
        }
    }
    interface buttonClickListener {
        fun onClicked(estimateScore: Int)
    }

    private lateinit var onClickedListener: buttonClickListener

    fun setOnClickedListener(listener:buttonClickListener){
        onClickedListener = listener
    }
}