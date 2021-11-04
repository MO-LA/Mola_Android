package com.example.molaschoolproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SchoolCategoryBottomSheet() : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.schoolcategory_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var typeText: String = "전체"

        view?.findViewById<Button>(R.id.button_bottom_sheet)?.setOnClickListener {
            onClickedListener.onClicked(typeText)
            dismiss()
        }
        view?.findViewById<TextView>(R.id.cate_all)?.setOnClickListener{
            val cateAll:TextView = view?.findViewById(R.id.cate_all)!!
            typeText = cateAll.text.toString()
        }
        view?.findViewById<TextView>(R.id.cate_general)?.setOnClickListener{
            val cateGeneral:TextView = view?.findViewById(R.id.cate_general)!!
            typeText = cateGeneral.text.toString()
        }
        view?.findViewById<TextView>(R.id.cate_automony)?.setOnClickListener{
            val cateAutomony:TextView = view?.findViewById(R.id.cate_automony)!!
            typeText = cateAutomony.text.toString()
        }
        view?.findViewById<TextView>(R.id.cate_specialized)?.setOnClickListener{
            val cateSpecialized:TextView = view?.findViewById(R.id.cate_specialized)!!
            typeText = cateSpecialized.text.toString()
        }
        view?.findViewById<TextView>(R.id.cate_specialpurpose)?.setOnClickListener{
            val cateSpecialpurpose:TextView = view?.findViewById(R.id.cate_specialpurpose)!!
            typeText = cateSpecialpurpose.text.toString()
        }
    }

    interface textClickListener {
        fun onClicked(typetext: String)
    }

    private lateinit var onClickedListener: textClickListener

    fun setOnClickedListener(listener:textClickListener){
        onClickedListener = listener
    }
}