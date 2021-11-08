package com.example.molaschoolproject.data_type

import java.io.Serializable

data class Estimate(
    var status: Int,
    var massage: String,
    var data: Double? = 0.0
) : Serializable