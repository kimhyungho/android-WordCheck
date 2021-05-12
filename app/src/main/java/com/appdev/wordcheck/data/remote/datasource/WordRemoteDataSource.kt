package com.appdev.wordcheck.data.remote.datasource

import com.appdev.wordcheck.data.model.network.response.ResponseAddWord
import com.appdev.wordcheck.data.model.network.response.ResponseDeleteWord
import com.appdev.wordcheck.data.model.network.response.ResponseGetList
import com.appdev.wordcheck.data.model.network.response.ResponseGetWord
import io.reactivex.Completable
import io.reactivex.Single

interface WordRemoteDataSource {
    fun getContentList(): Single<List<ResponseGetList>>
    fun getWordList(content: String): Single<List<ResponseGetWord>>
    fun addWord(
        contents: String,
        spelling: String,
        category: String,
        meaning: String
    ): Single<ResponseAddWord>

    fun deleteWord(id: Int): Single<ResponseDeleteWord>
}