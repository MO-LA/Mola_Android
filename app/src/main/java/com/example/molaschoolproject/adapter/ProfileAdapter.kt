package com.example.molaschoolproject.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.R
import com.example.molaschoolproject.activity.SchoolDetailActivity
import com.example.molaschoolproject.data_type.SchoolProfiles

class ProfileAdapter(val profileList: ArrayList<SchoolProfiles>) : RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.main_list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        var cnt = profileList.get(position).count.toString()
        cnt = "($cnt+)"
        holder.schoolName.text = profileList.get(position).schoolName
        holder.point.text = profileList.get(position).point.toString()
        holder.count.text = cnt
        holder.schoolGenderType.text = profileList.get(position).schoolGenderType
        holder.schoolType.text = "${profileList.get(position).schoolType} - ${profileList.get(position).schoolLocation}"

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView?.context, SchoolDetailActivity::class.java)
            ContextCompat.startActivity(holder.itemView.context,intent,null)
        }
    }

    override fun getItemCount(): Int {
        return profileList.size
    }
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val schoolName = itemView.findViewById<TextView>(R.id.tv_schoolname) // 학교명
        val point = itemView.findViewById<TextView>(R.id.tv_point) // 평점
        val count = itemView.findViewById<TextView>(R.id.tv_pick_count) // 평가한 유저 수
        val schoolGenderType = itemView.findViewById<TextView>(R.id.school_gendertype)
        val schoolType = itemView.findViewById<TextView>(R.id.school_type) // 학교 유형
    }

}