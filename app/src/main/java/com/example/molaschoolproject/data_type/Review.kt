package com.example.molaschoolproject.data_type

import java.io.Serializable

data class Review(
    var data: ArrayList<ReviewList>? = null,
    var massage: String,
    var status: Int
) : Serializable

data class ReviewList(
    var content: String? = null,
    var id: String? = null,
    var school: String? = null
) : Serializable