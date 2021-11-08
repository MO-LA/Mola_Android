package com.example.molaschoolproject.adapter

import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
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
        holder.wordContents.text = wordList.get(position).wordContents
        holder.wordLink.text = wordList.get(position).wordLink

//        val content = SpannableString(holder.wordLink.text.toString())
//        val url = "https://namu.wiki/w/%EC%9D%BC%EB%B0%98%EA%B3%84%20%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90"
//        content.setSpan(URLSpan(url), 0, content.length, 0)
//        holder.wordLink.setMovementMethod(LinkMovementMethod.getInstance())
//        holder.wordLink.text = content
//        holder.wordLink.text = wordList.get(position).wordLink
    }

    override fun getItemCount(): Int {
        return wordList.size
    }
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordName = itemView.findViewById<TextView>(R.id.tv_wordname) // 용어 이름
        val wordContents = itemView.findViewById<TextView>(R.id.tv_wordcontents) // 용어 설명 내용
        val wordLink = itemView.findViewById<TextView>(R.id.tv_word_weblink)


    }


}