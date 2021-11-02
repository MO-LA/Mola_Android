package com.example.molaschoolproject.data_type

import java.io.Serializable

data class community (
    val content: String? = null,
    val dateTime: String? = null,
    val id: String? = null,
    val idx: Int? = 0,
    val school: String? = null
) : Serializable