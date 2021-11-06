package com.example.molaschoolproject

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SchoolCategoryRegionBottomSheet() : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.schoolcategory_region_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val editRegion: EditText? = view?.findViewById(R.id.edit_region)

        view?.findViewById<Button>(R.id.button_category_region_bottom_sheet)?.setOnClickListener{
            val regionText: String = editRegion?.text.toString()
            onClickedListener.onClicked(regionText)
            dismiss()
        }
    }

    interface textClickListener {
        fun onClicked(regionText: String)
    }

    private lateinit var onClickedListener: textClickListener

    fun setOnClickedListener(listener:textClickListener){
        onClickedListener = listener
    }
}
