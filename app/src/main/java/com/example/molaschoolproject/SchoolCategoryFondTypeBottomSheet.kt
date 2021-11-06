package com.example.molaschoolproject

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SchoolCategoryFondTypeBottomSheet() : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.schoolcategory_fondtype_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var fondTypeText: String = "설립유형"

        val cateFondTypeAll: TextView? = view?.findViewById(R.id.cate_fondtype_all)
        val cateIndependence: TextView? = view?.findViewById(R.id.cate_independence)
        val cateEstablish: TextView? = view?.findViewById(R.id.cate_establish)
        val cateAccessories: TextView? = view?.findViewById(R.id.cate_accessories)

        view?.findViewById<Button>(R.id.button_category_fondtype_bottom_sheet)?.setOnClickListener{
            onClickedListener.onClicked(fondTypeText)
            dismiss()
        }

        cateFondTypeAll?.setOnClickListener{
            cateFondTypeAll.setTextColor(Color.BLUE)
            cateIndependence?.setTextColor(Color.BLACK)
            cateEstablish?.setTextColor(Color.BLACK)
            cateAccessories?.setTextColor(Color.BLACK)

            fondTypeText = cateFondTypeAll.text.toString()
        }

        cateIndependence?.setOnClickListener{
            cateFondTypeAll?.setTextColor(Color.BLACK)
            cateIndependence.setTextColor(Color.BLUE)
            cateEstablish?.setTextColor(Color.BLACK)
            cateAccessories?.setTextColor(Color.BLACK)

            fondTypeText = cateIndependence.text.toString()
        }

        cateEstablish?.setOnClickListener{
            cateFondTypeAll?.setTextColor(Color.BLACK)
            cateIndependence?.setTextColor(Color.BLACK)
            cateEstablish.setTextColor(Color.BLUE)
            cateAccessories?.setTextColor(Color.BLACK)

            fondTypeText = cateEstablish.text.toString()
        }

        cateAccessories?.setOnClickListener{
            cateFondTypeAll?.setTextColor(Color.BLACK)
            cateIndependence?.setTextColor(Color.BLACK)
            cateEstablish?.setTextColor(Color.BLACK)
            cateAccessories.setTextColor(Color.BLUE)

            fondTypeText = cateAccessories.text.toString()
        }

    }

    interface textClickListener {
        fun onClicked(fondTypeText: String)
    }

    private lateinit var onClickedListener: textClickListener

    fun setOnClickedListener(listener:textClickListener){
        onClickedListener = listener
    }
}
