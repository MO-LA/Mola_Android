package com.example.molaschoolproject.data_type

import java.io.Serializable

data class SendReview(
    var content: String,
    var schoolIdx: Int
) : Serializable