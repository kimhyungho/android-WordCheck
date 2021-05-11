package com.appdev.wordcheck.data.model.network.response

data class ResponseGetList(
    val list: List<A>
)

data class A(
    val contents: String
)