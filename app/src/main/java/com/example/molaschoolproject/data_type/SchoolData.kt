package com.example.molaschoolproject.data_type

import java.io.Serializable

data class SchoolData(
    var data: List<SchoolProfiles>? = null,
    var message: String? = null,
    var status: Int? = 0
): Serializable