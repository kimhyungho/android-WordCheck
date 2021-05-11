package com.appdev.wordcheck.data.model.network.response

data class ResponseGetWord(
    val id: Int,
    val contents: String,
    val spelling: String,
    val category: String,
    val meaning: String,
    val remember: Boolean,
    val wrong_count: Int,
    val account: Int
)