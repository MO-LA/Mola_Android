package com.example.molaschoolproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.R
import com.example.molaschoolproject.data_type.ReviewList


class CommentAdapter(val commentList: ArrayList<ReviewList>) : RecyclerView.Adapter<CommentAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_list_item,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentAdapter.CustomViewHolder, position: Int) {
        holder.reviewerId.text = commentList.get(position).id
        holder.reviewerSchool.text = commentList.get(position).school
        holder.content.text = commentList.get(position).content
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val reviewerId = itemView.findViewById<TextView>(R.id.tv_commenter)
        val reviewerSchool = itemView.findViewById<TextView>(R.id.tv_commenter_school)
        val content = itemView.findViewById<TextView>(R.id.tv_comment)

    }


}