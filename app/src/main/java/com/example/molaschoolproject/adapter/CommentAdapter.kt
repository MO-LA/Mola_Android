package com.example.molaschoolproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.R
import com.example.molaschoolproject.data_type.Comment


class CommentAdapter(val commentList: ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item_list,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentAdapter.CustomViewHolder, position: Int) {
        holder.commenter.text = commentList.get(position).commenter
        holder.commenterSchool.text = commentList.get(position).commenterSchool
        holder.comment.text = commentList.get(position).comment
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val commenter = itemView.findViewById<TextView>(R.id.tv_commenter)
        val commenterSchool = itemView.findViewById<TextView>(R.id.tv_commenter_school)
        val comment = itemView.findViewById<TextView>(R.id.tv_comment)

    }


}