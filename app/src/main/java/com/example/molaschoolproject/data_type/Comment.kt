package com.example.molaschoolproject.data_type

import java.io.Serializable

data class Comment(
    val data: ArrayList<CommentList>? = null,
    val massage: String,
    val status: Int
): Serializable

data class CommentList(
    val content: String? = null,
    val id: String? = null,
    val idx: Int? = 0,
    val school: String? = null
) : Serializable
