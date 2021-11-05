package com.example.molaschoolproject.data_type

class User(
    val data: userData? = null,
    val massage: String? = null,
    val status: Int? = 0

)
data class userData(
    val id: String? = null,
    val school: String? = null
)