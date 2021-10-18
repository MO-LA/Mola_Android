package com.example.molaschoolproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.molaschoolproject.R
import com.example.molaschoolproject.data_type.Word

class WordAdapter(val wordList: ArrayList<Word>) : RecyclerView.Adapter<WordAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wordsearch_list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.wordName.text = wordList.get(position).wordName
        holder.wordContents.text = wordList.get(position).wordcontents
    }

    override fun getItemCount(): Int {
        return wordList.size
    }
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordName = itemView.findViewById<TextView>(R.id.tv_wordname) // 용어 이름
        val wordContents = itemView.findViewById<TextView>(R.id.tv_wordcontents) // 용어 설명 내용

    }


}