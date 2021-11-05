package com.example.molaschoolproject.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.R
import com.example.molaschoolproject.activity.CommunityDetailActivity
import com.example.molaschoolproject.activity.CommunityWritingActivity
import com.example.molaschoolproject.data_type.Community
import com.example.molaschoolproject.data_type.ReviewList

class communityAdapter(
    val communityList: ArrayList<Community>
) : RecyclerView.Adapter<communityAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.community_list, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.communityTitle.text = communityList.get(position).title
        holder.communityId.text = communityList.get(position).id
        holder.communityTime.text = communityList.get(position).dateTime

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CommunityDetailActivity::class.java)
            intent.putExtra("title", communityList.get(position).title)
            intent.putExtra("id", communityList.get(position).id)
            intent.putExtra("dateTime", communityList.get(position).dateTime)
            intent.putExtra("idx", communityList.get(position).idx)
            intent.putExtra("content", communityList.get(position).content)
            val activityHolder: Activity = holder.itemView.context as Activity
            activityHolder.finish()
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return communityList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val communityTitle = itemView.findViewById<TextView>(R.id.community_title)
        val communityTime = itemView.findViewById<TextView>(R.id.community_time)
        val communityId = itemView.findViewById<TextView>(R.id.community_id)
    }

}