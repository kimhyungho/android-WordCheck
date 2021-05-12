package com.appdev.wordcheck.data.repository

import android.util.Log
import com.appdev.wordcheck.data.model.domain.Content
import com.appdev.wordcheck.data.model.domain.Word
import com.appdev.wordcheck.data.model.mapper.ContentMapper
import com.appdev.wordcheck.data.model.mapper.WordMapper
import com.appdev.wordcheck.data.remote.datasource.WordRemoteDataSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WordRepoImpl(
    private val wordRemoteDataSource: WordRemoteDataSource
) : WordRepo {
    override fun getContentList(): Single<List<Content>> {
        return wordRemoteDataSource.getContentList()
            .subscribeOn(Schedulers.io())
            .map {
                ContentMapper.map(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getContentWordList(content: String): Single<List<Word>> {
        return wordRemoteDataSource.getWordList(content)
            .subscribeOn(Schedulers.io())
            .map {
                WordMapper.map(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}