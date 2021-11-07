package com.example.molaschoolproject.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.R
import com.example.molaschoolproject.activity.LocalSearchActivity
import com.example.molaschoolproject.activity.SchoolSearchActivity
import com.example.molaschoolproject.data_type.SchoolProfiles

class SchoolSearchAdapter(
    val schoolSearchList: ArrayList<SchoolProfiles>
): RecyclerView.Adapter<SchoolSearchAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.school_search_list_item, parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.schoolSearchSchoolName.text = schoolSearchList.get(position).schoolName
        holder.schoolSearchArea.text = schoolSearchList.get(position).address

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return schoolSearchList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val schoolSearchSchoolName = itemView.findViewById<TextView>(R.id.schoolSearch_schoolName)
        val schoolSearchArea = itemView.findViewById<TextView>(R.id.schoolSearch_area)
    }
}