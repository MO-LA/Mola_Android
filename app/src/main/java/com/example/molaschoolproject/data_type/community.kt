package com.example.molaschoolproject.data_type

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

class Community(
    val content: String? = null,
    val dateTime: String? = null,
    val id: String? = null,
    val idx: Int? = 0,
    val school: String? = null,
    val title: String? = null
) : Serializable