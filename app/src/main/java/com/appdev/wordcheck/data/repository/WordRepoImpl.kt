package com.appdev.wordcheck.data.repository

import android.util.Log
import com.appdev.wordcheck.data.model.domain.Content
import com.appdev.wordcheck.data.model.domain.Word
import com.appdev.wordcheck.data.model.mapper.ContentMapper
import com.appdev.wordcheck.data.model.mapper.WordMapper
import com.appdev.wordcheck.data.remote.datasource.WordRemoteDataSource
import io.reactivex.Completable
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

    override fun addWord(
        contents: String,
        spelling: String,
        category: String,
        meaning: String
    ): Completable {
        return Completable.fromSingle(
            wordRemoteDataSource.addWord(contents, spelling, category, meaning)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    override fun deleteWord(id: Int): Completable {
        return Completable.fromSingle(
            wordRemoteDataSource.deleteWord(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    override fun searchWord(target: String): Single<List<Word>> {
        return wordRemoteDataSource.searchWord(target)
            .subscribeOn(Schedulers.io())
            .map {
                WordMapper.map(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }


    override fun scoreWord(id: Int, state: String): Completable {
        return Completable.fromSingle(
            wordRemoteDataSource.scoreWord(id, state)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }

    override fun getWrongWord(wrong_count: Int): Single<List<Word>> {
        return wordRemoteDataSource.getWrongWord(wrong_count)
            .subscribeOn(Schedulers.io())
            .map {
                WordMapper.map(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}