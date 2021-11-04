package com.example.molaschoolproject.data_type

import java.io.Serializable

data class CommunityData (
    val data: List<Community>,
    val status: Int? = 0,
    val message: String
) : Serializable