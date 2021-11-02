package com.example.molaschoolproject.data_type

import java.io.Serializable

data class SchoolData(
    var data: List<SchoolProfiles>,
    var message: String,
    var status: Int
): Serializable