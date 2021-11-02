package com.example.molaschoolproject.data_type

import java.io.Serializable

data class token(
    var data: Data? = null,
    var status: Int? = 0,
    var message: String
) : Serializable

data class Data(
    var accessToken: String? = null,
    var refreshToken: String? = null
) : Serializable