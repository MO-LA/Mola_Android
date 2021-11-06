package com.example.molaschoolproject.data_type

import java.io.Serializable

data class SchoolDetail(
    var status: Int,
    var massage: String,
    var data: List<SchoolDetailData>? = null
)

data class SchoolDetailData(
    var idx: Int? = 0,
    var schoolName: String? = null,
    var administrativeOfficeTel: String? = null, //행정실 전화번호
    var staffroomTel: String? = null, //교무실 전화번호
    var homePage: String? = null,
    var genderCheck: String,
    var roadNameAddress: String,
    var schoolKind: String,
    var fondType: String,
    var fond: String,
    var maleSum: Int? = 0,
    var femaleSum: Int? = 0
) : Serializable