package com.example.molaschoolproject

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SchoolCategoryFondBottomSheet(): BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.schoolcategory_fond_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var fondText: String = "설립구분"

        val cateFondAll: TextView? = view?.findViewById(R.id.cate_fond_all)
        val catePrivate: TextView? = view?.findViewById(R.id.cate_private)
        val cateNational: TextView? = view?.findViewById(R.id.cate_national)
        val catePublic: TextView? = view?.findViewById(R.id.cate_public)

        view?.findViewById<Button>(R.id.button_category_fond_bottom_sheet)?.setOnClickListener{
            onClickedListener.onClicked(fondText)
            dismiss()
        }

        cateFondAll?.setOnClickListener{
            cateFondAll.setTextColor(Color.BLUE)
            catePrivate?.setTextColor(Color.BLACK)
            cateNational?.setTextColor(Color.BLACK)
            catePublic?.setTextColor(Color.BLACK)

            fondText = cateFondAll.text.toString()
        }
        catePrivate?.setOnClickListener{
            cateFondAll?.setTextColor(Color.BLACK)
            catePrivate.setTextColor(Color.BLUE)
            cateNational?.setTextColor(Color.BLACK)
            catePublic?.setTextColor(Color.BLACK)

            fondText = catePrivate.text.toString()
        }
        cateNational?.setOnClickListener{
            cateFondAll?.setTextColor(Color.BLACK)
            catePrivate?.setTextColor(Color.BLACK)
            cateNational.setTextColor(Color.BLUE)
            catePublic?.setTextColor(Color.BLACK)

            fondText = cateNational.text.toString()
        }
        catePublic?.setOnClickListener{
            cateFondAll?.setTextColor(Color.BLACK)
            catePrivate?.setTextColor(Color.BLACK)
            cateNational?.setTextColor(Color.BLACK)
            catePublic.setTextColor(Color.BLUE)

            fondText = catePublic.text.toString()
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