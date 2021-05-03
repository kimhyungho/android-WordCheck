package com.appdev.wordcheck.data.model.network.request

data class RequestNormalSignUp(
    val nickname: String,
    val password: String,
    val secret_code: String
)