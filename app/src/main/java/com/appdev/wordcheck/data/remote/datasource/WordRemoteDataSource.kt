package com.appdev.wordcheck.data.remote.datasource

import com.appdev.wordcheck.data.model.network.response.ResponseGetList
import com.appdev.wordcheck.data.model.network.response.ResponseGetWord
import io.reactivex.Single

interface WordRemoteDataSource {
    fun getContentList(): Single<List<ResponseGetList>>
    fun getWordList(content: String): Single<List<ResponseGetWord>>
}