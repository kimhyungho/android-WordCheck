package com.appdev.wordcheck.data.remote.datasource

import com.appdev.wordcheck.data.model.network.response.ResponseGetList
import com.appdev.wordcheck.data.remote.api.WordService
import com.appdev.wordcheck.util.LoginPreference
import io.reactivex.Single

class WordRemoteDataSourceImpl(private val service: WordService) : WordRemoteDataSource {
    override fun getList(): Single<List<ResponseGetList>> {
        val auth_token = LoginPreference.getUserAccountToken()
        return service.getList(auth_token)
    }
}