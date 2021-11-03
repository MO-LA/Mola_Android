package com.example.molaschoolproject.data_type

import java.io.Serializable

class SignUp(
    var id: String? = null,
    var password: String? = null,
    var age: Int? = 0,
    var sex: String? = null,
    var residentialArea: String? = null,
    var school: String? = null
) : Serializable