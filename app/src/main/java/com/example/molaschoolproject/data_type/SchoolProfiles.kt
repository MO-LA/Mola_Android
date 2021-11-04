package com.example.molaschoolproject.data_type

import java.io.Serializable

class SchoolProfiles(
    var idx: Int? = 0,
    var schoolName:String? = null,
    var estimate:Double,
    var estimatedPeople: Int? = 0,
    var genderCheck:String? = null,
    var fondType:String? = null,
    val roadNameAddress: String? = null
): Serializable