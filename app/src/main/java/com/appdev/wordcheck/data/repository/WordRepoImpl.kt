package com.appdev.wordcheck.data.repository

import android.util.Log
import com.appdev.wordcheck.data.model.domain.Content
import com.appdev.wordcheck.data.model.mapper.ContentMapper
import com.appdev.wordcheck.data.remote.datasource.WordRemoteDataSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WordRepoImpl(
    private val wordRemoteDataSource: WordRemoteDataSource
) : WordRepo {
    override fun getContentList(): Single<List<Content>> {
        return wordRemoteDataSource.getList()
            .subscribeOn(Schedulers.io())
            .map {
                ContentMapper.map(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addWord(word: String) {
    }
}