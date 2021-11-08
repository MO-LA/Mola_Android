package com.example.molaschoolproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.R
import com.example.molaschoolproject.data_type.CommentList

class ReviewAdapter(
    val reviewList: ArrayList<CommentList>
) : RecyclerView.Adapter<ReviewAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.review_list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.commentId.text = reviewList.get(position).id
        holder.commentSchool.text = reviewList.get(position).school
        holder.commentContent.text = reviewList.get(position).content
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commentId = itemView.findViewById<TextView>(R.id.review_id)
        val commentSchool = itemView.findViewById<TextView>(R.id.review_school)
        val commentContent = itemView.findViewById<TextView>(R.id.review_content)
    }
}