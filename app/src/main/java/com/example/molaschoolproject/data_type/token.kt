package com.example.molaschoolproject.data_type

import java.io.Serializable

data class token(
    var data: tokenData? = null,
    var status: Int? = 0,
    var message: String
) : Serializable

data class tokenData(
    var accessToken: String? = null,
    var refreshToken: String? = null
) : Serializable