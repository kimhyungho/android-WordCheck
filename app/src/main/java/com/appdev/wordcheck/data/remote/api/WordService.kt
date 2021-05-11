package com.appdev.wordcheck.data.remote.api

import com.appdev.wordcheck.data.model.network.response.ResponseGetList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface WordService {
    @GET("words/")
    fun getList(
        @Header("authorization") account_token: String
    ): Single<ResponseGetList>
}