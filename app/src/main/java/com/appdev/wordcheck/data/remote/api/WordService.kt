package com.appdev.wordcheck.data.remote.api

import com.appdev.wordcheck.data.model.network.response.ResponseGetList
import com.appdev.wordcheck.data.model.network.response.ResponseGetWord
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WordService {
    @GET("words/")
    fun getList(
        @Header("authorization") account_token: String
    ): Single<List<ResponseGetList>>

    @GET("words/detail_list/")
    fun getContentWord(
        @Header("authorization") account_token: String,
        @Query("contents") contents: String
    ): Single<List<ResponseGetWord>>

    @GET("words/detail_list/")
    fun getContentWord(
        @Header("authorization") account_token: String,
        @Query("contents") contents: String,
        @Query("wrong_count") wrong_count: Int
    ): Single<List<ResponseGetWord>>
}