package com.example.molaschoolproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

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

        val starOne = view?.findViewById<ImageView>(R.id.assessment_star_one)
        val starTwo = view?.findViewById<ImageView>(R.id.assessment_star_two)
        val starThree = view?.findViewById<ImageView>(R.id.assessment_star_three)
        val starFour = view?.findViewById<ImageView>(R.id.assessment_star_four)
        val starFive = view?.findViewById<ImageView>(R.id.assessment_star_five)

        starOne?.setOnClickListener {
            starOne?.setImageResource(R.drawable.ic_baseline_star_24)
            starTwo?.setImageResource(R.drawable.ic_baseline_star_border_24)
            starThree?.setImageResource(R.drawable.ic_baseline_star_border_24)
            starFour?.setImageResource(R.drawable.ic_baseline_star_border_24)
            starFive?.setImageResource(R.drawable.ic_baseline_star_border_24)
        }
        starTwo?.setOnClickListener {
            starOne?.setImageResource(R.drawable.ic_baseline_star_24)
            starTwo?.setImageResource(R.drawable.ic_baseline_star_24)
            starThree?.setImageResource(R.drawable.ic_baseline_star_border_24)
            starFour?.setImageResource(R.drawable.ic_baseline_star_border_24)
            starFive?.setImageResource(R.drawable.ic_baseline_star_border_24)
        }
        starThree?.setOnClickListener {
            starOne?.setImageResource(R.drawable.ic_baseline_star_24)
            starTwo?.setImageResource(R.drawable.ic_baseline_star_24)
            starThree?.setImageResource(R.drawable.ic_baseline_star_24)
            starFour?.setImageResource(R.drawable.ic_baseline_star_border_24)
            starFive?.setImageResource(R.drawable.ic_baseline_star_border_24)
        }
        starFour?.setOnClickListener {
            starOne?.setImageResource(R.drawable.ic_baseline_star_24)
            starTwo?.setImageResource(R.drawable.ic_baseline_star_24)
            starThree?.setImageResource(R.drawable.ic_baseline_star_24)
            starFour?.setImageResource(R.drawable.ic_baseline_star_24)
            starFive?.setImageResource(R.drawable.ic_baseline_star_border_24)
        }
        starFive?.setOnClickListener {
            starOne?.setImageResource(R.drawable.ic_baseline_star_24)
            starTwo?.setImageResource(R.drawable.ic_baseline_star_24)
            starThree?.setImageResource(R.drawable.ic_baseline_star_24)
            starFour?.setImageResource(R.drawable.ic_baseline_star_24)
            starFive?.setImageResource(R.drawable.ic_baseline_star_24)
        }

        view?.findViewById<Button>(R.id.btn_assessment_confirm)?.setOnClickListener{
            dismiss()
        }
    }
}