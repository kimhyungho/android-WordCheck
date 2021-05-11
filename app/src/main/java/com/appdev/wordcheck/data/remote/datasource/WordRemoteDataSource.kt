package com.appdev.wordcheck.data.remote.datasource

import com.appdev.wordcheck.data.model.network.response.ResponseGetList
import io.reactivex.Single

interface WordRemoteDataSource {
    fun getList(): Single<List<ResponseGetList>>
}