package com.appdev.wordcheck.data.remote.datasource

import com.appdev.wordcheck.data.model.network.response.ResponseAddWord
import com.appdev.wordcheck.data.model.network.response.ResponseDeleteWord
import com.appdev.wordcheck.data.model.network.response.ResponseGetList
import com.appdev.wordcheck.data.model.network.response.ResponseGetWord
import com.appdev.wordcheck.data.remote.api.WordService
import com.appdev.wordcheck.util.LoginPreference
import io.reactivex.Single

class WordRemoteDataSourceImpl(private val service: WordService) : WordRemoteDataSource {
    override fun getContentList(): Single<List<ResponseGetList>> {
        val auth_token = LoginPreference.getUserAccountToken()
        return service.getList(auth_token)
    }

    override fun getWordList(content: String): Single<List<ResponseGetWord>> {
        val auth_token = LoginPreference.getUserAccountToken()
        return service.getContentWord(auth_token, content)
    }

    override fun addWord(
        contents: String,
        spelling: String,
        category: String,
        meaning: String
    ): Single<ResponseAddWord> {
        val auth_token = LoginPreference.getUserAccountToken()
        return service.addWord(auth_token, contents, spelling, category, meaning)
    }

    override fun deleteWord(id: Int): Single<ResponseDeleteWord> {
        val auth_token = LoginPreference.getUserAccountToken()
        return service.deleteWord(auth_token, id)
    }
}