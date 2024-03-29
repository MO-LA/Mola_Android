package com.example.molaschoolproject.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.R
import com.example.molaschoolproject.activity.SchoolDetailActivity
import com.example.molaschoolproject.data_type.SchoolProfiles
import kotlin.math.round

class ProfileAdapter(val profileList: ArrayList<SchoolProfiles>) : RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.main_list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.schoolName.text = profileList.get(position).schoolName

        var estimate: Double = profileList.get(position).estimate
        estimate = round((estimate * 10)) / 10

        holder.estimate.text = estimate.toString()

        var estimatedPeople: String = profileList.get(position).estimatedPeople.toString()
        estimatedPeople = "($estimatedPeople+)"
        holder.estimatedPeople.text = estimatedPeople

        var genderCheck: String? = profileList.get(position).genderCheck
        if (genderCheck == "M") genderCheck = "남고"
        else if (genderCheck == "W") genderCheck = "여고"
        else if (genderCheck == "MW") genderCheck = "남녀공학"
        holder.genderCheck.text = genderCheck

        var fondType: String? = profileList.get(position).fondType
        if (fondType == "GENERAL") fondType = "일반고"
        else if (fondType == "SPECIAL_PURPOSE") fondType = "특수목적고"
        else if (fondType == "AUTONOMOUS") fondType = "자율고"
        else if (fondType == "SPECIALIZED") fondType = "특성화고"

        var address: String? = profileList.get(position).address
        holder.fondType.text = "$fondType - $address"

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView?.context, SchoolDetailActivity::class.java)
            intent.putExtra("schoolIdx",profileList.get(position).idx)
            val activityHolder: Activity = holder.itemView.context as Activity
            val contextText: String = holder.itemView.context.toString()

            if (contextText.contains("MainActivity")) intent.putExtra("context","M")
            else if (contextText.contains("MyPickActivity")) intent.putExtra("context","P")
            activityHolder.finish()
            ContextCompat.startActivity(holder.itemView.context,intent,null)
        }
    }

    override fun getItemCount(): Int {
        return profileList.size
    }
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val schoolName = itemView.findViewById<TextView>(R.id.tv_schoolname) // 학교명
        val estimate = itemView.findViewById<TextView>(R.id.tv_point) // 평점
        val estimatedPeople = itemView.findViewById<TextView>(R.id.tv_pick_count) // 평가한 유저 수
        val genderCheck = itemView.findViewById<TextView>(R.id.school_gendertype) // 성별타입
        val fondType = itemView.findViewById<TextView>(R.id.school_type) // 학교 유형
    }

}