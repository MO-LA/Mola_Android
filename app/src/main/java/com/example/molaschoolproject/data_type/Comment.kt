package com.example.molaschoolproject.data_type

class Comment(
    val data: ArrayList<CommentList>? = null,
    val status: Int? = 0,
    val message: String
)

data class CommentList(
    val content: String? = null,
    val id: String? = null,
    val idx: Int? = 0,
    val school: String? = null
)