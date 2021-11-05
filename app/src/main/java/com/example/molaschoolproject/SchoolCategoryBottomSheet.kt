package com.example.molaschoolproject

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SchoolCategoryBottomSheet() : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.schoolcategory_kind_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var typeText: String = "학교유형"

        val cateAll: TextView? = view?.findViewById<TextView>(R.id.cate_all)
        val cateGeneral: TextView? = view?.findViewById<TextView>(R.id.cate_general)
        val cateAutomony: TextView? = view?.findViewById<TextView>(R.id.cate_automony)
        val cateSpecialized: TextView? = view?.findViewById<TextView>(R.id.cate_specialized)
        val cateSpecialpurpose: TextView? = view?.findViewById<TextView>(R.id.cate_specialpurpose)
        view?.findViewById<Button>(R.id.button_bottom_sheet)?.setOnClickListener {
            onClickedListener.onClicked(typeText)
            dismiss()
        }
        cateAll?.setOnClickListener{
            cateAll.setTextColor(Color.BLUE)
            cateGeneral?.setTextColor(Color.BLACK)
            cateAutomony?.setTextColor(Color.BLACK)
            cateSpecialized?.setTextColor(Color.BLACK)
            cateSpecialpurpose?.setTextColor(Color.BLACK)

            typeText = cateAll.text.toString()
        }
        cateGeneral?.setOnClickListener{
            cateAll?.setTextColor(Color.BLACK)
            cateGeneral.setTextColor(Color.BLUE)
            cateAutomony?.setTextColor(Color.BLACK)
            cateSpecialized?.setTextColor(Color.BLACK)
            cateSpecialpurpose?.setTextColor(Color.BLACK)

            typeText = cateGeneral.text.toString()
        }
        cateAutomony?.setOnClickListener{
            cateAll?.setTextColor(Color.BLACK)
            cateGeneral?.setTextColor(Color.BLACK)
            cateAutomony.setTextColor(Color.BLUE)
            cateSpecialized?.setTextColor(Color.BLACK)
            cateSpecialpurpose?.setTextColor(Color.BLACK)

            typeText = cateAutomony.text.toString()
        }
        cateSpecialized?.setOnClickListener{
            cateAll?.setTextColor(Color.BLACK)
            cateGeneral?.setTextColor(Color.BLACK)
            cateAutomony?.setTextColor(Color.BLACK)
            cateSpecialized.setTextColor(Color.BLUE)
            cateSpecialpurpose?.setTextColor(Color.BLACK)

            typeText = cateSpecialized.text.toString()
        }
        cateSpecialpurpose?.setOnClickListener{
            cateAll?.setTextColor(Color.BLACK)
            cateGeneral?.setTextColor(Color.BLACK)
            cateAutomony?.setTextColor(Color.BLACK)
            cateSpecialized?.setTextColor(Color.BLACK)
            cateSpecialpurpose.setTextColor(Color.BLUE)

            typeText = cateSpecialpurpose.text.toString()
        }
    }

    interface textClickListener {
        fun onClicked(typeText: String)
    }

    private lateinit var onClickedListener: textClickListener

    fun setOnClickedListener(listener:textClickListener){
        onClickedListener = listener
    }
}